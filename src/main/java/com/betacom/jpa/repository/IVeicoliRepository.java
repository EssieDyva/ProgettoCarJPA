package com.betacom.jpa.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.betacom.jpa.models.Veicoli;

@Repository
public interface IVeicoliRepository extends JpaRepository<Veicoli, Integer> {

    @Query(name = "veicoli.selectAll")
    List<Veicoli> selectAll();
}
