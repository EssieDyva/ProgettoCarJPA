package com.betacom.jpa.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.betacom.jpa.models.TipoAlimentazione;

@Repository
public interface ITipoAlimentazioneRepository extends JpaRepository<TipoAlimentazione, Integer> {

	Boolean findByDescription(String description);
}
