package com.betacom.jpa.services.implementations;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.betacom.jpa.dto.inputs.SospensioniRequest;
import com.betacom.jpa.dto.outputs.SospensioniDTO;
import com.betacom.jpa.exception.AcademyException;
import com.betacom.jpa.models.Sospensioni;
import com.betacom.jpa.repository.ISospensioniRepository;
import com.betacom.jpa.services.interfaces.ISospensioniServices;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class SospensioniImpl implements ISospensioniServices {

    @Autowired
    private ISospensioniRepository sospensioniR;

    @Override
    @Transactional(rollbackFor = AcademyException.class)
    public void create(SospensioniRequest req) throws Exception {
        log.debug("create {}", req);
        Sospensioni marca = new Sospensioni();
        marca.setDescription(req.getDescription());
        sospensioniR.save(marca);
    }

    @Override
    @Transactional(rollbackFor = AcademyException.class)
    public void update(SospensioniRequest req) throws Exception {
        log.debug("update {}", req);
        Optional<Sospensioni> m = sospensioniR.findById(req.getId()); 
        if (m.isEmpty()) {
            throw new AcademyException("Marca non trovata in DB");
        }
        Sospensioni marca = m.get();
        marca.setDescription(req.getDescription());
        sospensioniR.save(marca);
    }

    @Override
    @Transactional(rollbackFor = AcademyException.class)
    public void delete(Integer id) throws Exception {
        log.debug("delete {}", id);
        sospensioniR.deleteById(id);
    }

    @Override
    public List<SospensioniDTO> list() {
        log.debug("listSospensioni");
        List<Sospensioni> mR =  sospensioniR.findAll();
        return mR.stream()
                .map(m -> SospensioniDTO.builder()
                        .id(m.getId())
                        .description(m.getDescription())
                        .build())
                .collect(Collectors.toList());
    }
}