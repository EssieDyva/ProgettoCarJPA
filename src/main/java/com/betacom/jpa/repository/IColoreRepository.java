package com.betacom.jpa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.betacom.jpa.models.Colore;

@Repository
public interface IColoreRepository extends JpaRepository<Colore, Integer> {

	Boolean findByDescription(String description);
}
