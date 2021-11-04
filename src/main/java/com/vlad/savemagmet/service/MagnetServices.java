package com.vlad.savemagmet.service;

import com.vlad.savemagmet.entity.Magnet;
import com.vlad.savemagmet.repository.MagnetRepository;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.Optional;

@Service
public class MagnetServices {


    @Autowired
    MagnetRepository repository;
    @PersistenceContext
    private EntityManager entityManager;

    public Magnet saveMagnet(Magnet magnet) {
        return repository.save(magnet);
    }

    public void saveMagnets(Magnet magnet) {
        Session session = null;
        Transaction tr = null;
        try {
            session = entityManager.unwrap(Session.class);
            tr = session.beginTransaction();
            String[] lines = magnet.getTitle().split("\\r?\\n");
            long i = magnet.getId();
            for (String line : lines) {
                Magnet tempMagnet = new Magnet(line);
                tempMagnet.setId(i);
                tempMagnet.setTitle(line);
                session.save(tempMagnet);
                i++;
            }
        } catch (Exception e) {
            System.out.println(e);
            tr.rollback();
        } finally {
            tr.commit();
            session.close();
        }
    }

    public List<Magnet> getMagnetInfos() {
        return repository.findAll();
    }

    public Optional<Magnet> getMagnetById(long id) {
        return repository.findById(id);
    }

    public boolean checkExistedMagnet(long id) {
        if (repository.existsById((long) id)) {
            return true;
        }
        return false;
    }

    public Magnet updateMagnet(Magnet magnet) {
        return repository.save(magnet);
    }

    public void deleteMagnetById(long id) {
        repository.deleteById(id);
    }

    public void deleteAlMagnets() {
        repository.deleteAll();
    }
}