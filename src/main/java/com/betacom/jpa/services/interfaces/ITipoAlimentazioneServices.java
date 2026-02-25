package com.betacom.jpa.services.interfaces;

import java.util.List;

import com.betacom.jpa.dto.inputs.TipoAlimentazioneRequest;
import com.betacom.jpa.dto.outputs.TipoAlimentazioneDTO;

public interface ITipoAlimentazioneServices {

	void create(TipoAlimentazioneRequest req) throws Exception;
	void update(TipoAlimentazioneRequest req) throws Exception;
	void delete(Integer id) throws Exception;
	List<TipoAlimentazioneDTO> list() throws Exception;
}
