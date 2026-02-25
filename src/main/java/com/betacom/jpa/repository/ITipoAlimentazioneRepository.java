package com.betacom.jpa.repository;


import org.springframework.data.jpa.repository.JpaRepository;

import com.betacom.jpa.models.TipoAlimentazione;


public interface ITipoAlimentazioneRepository extends JpaRepository<TipoAlimentazione, Integer> {

	Boolean findByDescription(String description);
}
