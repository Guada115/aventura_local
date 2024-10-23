package com.example.repository;

import com.example.entidades.Aventura;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AventuraRepository extends JpaRepository<Aventura, Long> {
}
