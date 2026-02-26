package com.betacom.jpa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


import com.betacom.jpa.models.Sospensioni;

@Repository
public interface ISospensioniRepository extends JpaRepository<Sospensioni, Integer>{

    Boolean findByDescription(String description);

}

