package com.betacom.jpa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.betacom.jpa.models.Macchina;

@Repository
public interface IMacchinaRepository extends JpaRepository<Macchina, Integer> {

}

