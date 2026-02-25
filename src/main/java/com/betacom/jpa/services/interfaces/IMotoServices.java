package com.betacom.jpa.services.interfaces;

import java.util.List;
import com.betacom.jpa.dto.inputs.MotoRequest;
import com.betacom.jpa.dto.outputs.MotoDTO;

public interface IMotoServices {

    void create(MotoRequest req) throws Exception;
    void update(MotoRequest req) throws Exception;
    void delete(Integer id) throws Exception;

    List<MotoDTO> list();
    
}