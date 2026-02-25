package com.betacom.jpa.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.betacom.jpa.models.Colore;

public interface IColoreRepository extends JpaRepository<Colore, Integer> {

	Boolean findByDescription(String description);
}
