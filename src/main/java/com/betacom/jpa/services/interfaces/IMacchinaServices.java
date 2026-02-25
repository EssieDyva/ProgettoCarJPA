package com.betacom.jpa.services.interfaces;


import java.util.List;

import com.betacom.jpa.dto.inputs.MacchinaRequest;
import com.betacom.jpa.dto.outputs.MacchinaDTO;


public interface IMacchinaServices {

	void create(MacchinaRequest req) throws Exception;
	void update(MacchinaRequest req) throws Exception;
	void delete(Integer id) throws Exception;

	List<MacchinaDTO> list();
}
