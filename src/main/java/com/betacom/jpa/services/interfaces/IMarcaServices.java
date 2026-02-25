package com.betacom.jpa.services.interfaces;


import java.util.List;

import com.betacom.jpa.dto.inputs.MarcaRequest;
import com.betacom.jpa.dto.outputs.MarcaDTO;

public interface IMarcaServices {



	void create(MarcaRequest req) throws Exception;
	void update(MarcaRequest req) throws Exception;
	void delete(Integer id) throws Exception;

	List<MarcaDTO> list();
}
