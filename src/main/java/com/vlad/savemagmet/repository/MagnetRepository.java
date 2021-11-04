package com.vlad.savemagmet.repository;

import com.vlad.savemagmet.entity.Magnet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MagnetRepository extends JpaRepository<Magnet, Long> {
}