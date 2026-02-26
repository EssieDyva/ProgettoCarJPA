package com.betacom.jpa.services.interfaces;


import java.util.List;

import com.betacom.jpa.dto.inputs.SospensioniRequest;
import com.betacom.jpa.dto.outputs.SospensioniDTO;

public interface ISospensioniServices {

	void create(SospensioniRequest req) throws Exception;
	void update(SospensioniRequest req) throws Exception;
	void delete(Integer id) throws Exception;

	List<SospensioniDTO> list();
}
