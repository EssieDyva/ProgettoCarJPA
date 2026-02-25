package com.betacom.jpa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.betacom.jpa.models.Freni;

@Repository
public interface IFreniRepository extends JpaRepository<Freni, Integer>{

    Boolean findByDescription(String description);
}