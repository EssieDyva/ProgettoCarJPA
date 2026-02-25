package com.betacom.jpa.services.interfaces;

import java.util.List;

import com.betacom.jpa.dto.inputs.ColoreRequest;
import com.betacom.jpa.dto.outputs.ColoreDTO;

public interface IColoreServices {
	void create(ColoreRequest req) throws Exception;
	void update(ColoreRequest req) throws Exception;
	void delete(Integer id) throws Exception;
	
	List<ColoreDTO> list();

}
