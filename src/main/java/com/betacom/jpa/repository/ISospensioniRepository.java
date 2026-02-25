package com.betacom.jpa.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


import com.betacom.jpa.models.Sospensioni;

@Repository
public interface ISospensioniRepository extends JpaRepository<Sospensioni, Integer>{

}

