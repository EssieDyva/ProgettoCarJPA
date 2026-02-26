package com.betacom.jpa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.betacom.jpa.models.Categoria;

@Repository
public interface ICategoriaRepository extends JpaRepository<Categoria, Integer> {
   
    Boolean findByDescrizione(String descrizione);
}