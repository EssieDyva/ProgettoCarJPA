package com.betacom.jpa.services.interfaces;

import java.util.List;

import com.betacom.jpa.dto.inputs.BiciRequest;
import com.betacom.jpa.dto.outputs.BiciDTO;

public interface IBiciServices {
	
	void create(BiciRequest req) throws Exception;
	void update(BiciRequest req) throws Exception;
	void delete(Integer id) throws Exception;
	
	List<BiciDTO> list();

}
