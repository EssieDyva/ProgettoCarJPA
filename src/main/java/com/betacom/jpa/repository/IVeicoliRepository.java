package com.betacom.jpa.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.betacom.jpa.models.Veicoli;

@Repository
public interface IVeicoliRepository extends JpaRepository<Veicoli, Integer> {

    @Query(name = "veicoli.selectByFilter")
    List<Veicoli> selectAllByFilter(@Param("id") Integer id,
                                    @Param("colore") String colore,
                                    @Param("categoria") String categoria,
                                    @Param("annoProduzione") Integer annoProduzione);

    @Query(name = "veicoli.selectByTarga")
    List<Veicoli> selectByTarga(@Param("targa") String targa);
}
