package com.betacom.jpa.services.interfaces;


import java.util.List;

import com.betacom.jpa.dto.inputs.FreniRequest;
import com.betacom.jpa.dto.outputs.FreniDTO;


public interface IFreniServices {

	List<FreniDTO> listFreni();

	void create(FreniRequest req) throws Exception;
	void update(FreniRequest req) throws Exception;
	void delete(Integer id) throws Exception;
}
