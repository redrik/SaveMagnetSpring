package com.vlad.savemagmet.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.vlad.savemagmet.entity.Magnet;

@Repository
public interface MagnetRepository extends JpaRepository<Magnet, Long>{
}