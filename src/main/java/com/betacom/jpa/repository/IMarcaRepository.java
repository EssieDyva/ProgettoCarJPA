package com.betacom.jpa.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.betacom.jpa.models.Marca;

@Repository
public interface IMarcaRepository extends JpaRepository<Marca, Integer>{

}
