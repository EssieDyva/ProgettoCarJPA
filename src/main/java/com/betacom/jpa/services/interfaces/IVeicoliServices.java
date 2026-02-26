package com.betacom.jpa.services.interfaces;

import java.util.List;

import com.betacom.jpa.dto.inputs.VeicoliRequest;
import com.betacom.jpa.dto.outputs.VeicoliDTO;
import com.betacom.jpa.models.Veicoli;

public interface IVeicoliServices {

    /**
	 * Questo servizio viene principalmente usato da Macchina, Moto e Bici per la loro creazione.
	 * Il processo logico attuale è:
	 * 1-creo prima un veicolo dalla quale estraggo l'ID univoco
	 * 2-l'ID univoco estratto lo uso come ID per Macchina, Moto o Bici
     */
    Veicoli create(VeicoliRequest req) throws Exception;

    void update(VeicoliRequest req) throws Exception;
    void delete(Integer id) throws Exception;
    List<VeicoliDTO> list() throws Exception;
}
