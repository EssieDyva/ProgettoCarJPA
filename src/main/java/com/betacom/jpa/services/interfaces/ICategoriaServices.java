package com.betacom.jpa.services.interfaces;

import java.util.List;
import com.betacom.jpa.dto.inputs.CategoriaRequest;
import com.betacom.jpa.dto.outputs.CategoriaDTO;

public interface ICategoriaServices {
    Integer create(CategoriaRequest req) throws Exception;
    void update(CategoriaRequest req) throws Exception;
    void delete(Integer id) throws Exception;
    List<CategoriaDTO> findAll();
}