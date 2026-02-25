package com.betacom.jpa.services.interfaces;

import java.util.List;

import com.betacom.jpa.dto.inputs.VeicoliRequest;
import com.betacom.jpa.dto.outputs.VeicoliDTO;

public interface IVeicoliServices {

    void create(VeicoliRequest req) throws Exception;
	void update(VeicoliRequest req) throws Exception;
	void delete(Integer id) throws Exception;
	List<VeicoliDTO> list() throws Exception;
}
