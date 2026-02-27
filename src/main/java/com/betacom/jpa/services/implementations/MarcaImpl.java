package com.betacom.jpa.services.implementations;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.betacom.jpa.exception.AcademyException;

import com.betacom.jpa.dto.inputs.MarcaRequest;
import com.betacom.jpa.dto.outputs.MarcaDTO;
import com.betacom.jpa.models.Marca;
import com.betacom.jpa.repository.IMarcaRepository;
import com.betacom.jpa.services.interfaces.IMarcaServices;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class MarcaImpl implements IMarcaServices {

    @Autowired
    private IMarcaRepository marcaR;

    @Override
    @Transactional(rollbackFor = AcademyException.class)
    public void create(MarcaRequest req) throws Exception {
        log.debug("create {}", req);

        Boolean exist = marcaR.findByDescription(req.getDescription());
		
		if (exist != null)
			throw new AcademyException("Categoria già presente in DB");

        Marca marca = new Marca();
        marca.setDescription(req.getDescription());
        marcaR.save(marca);
    }

    @Override
    @Transactional(rollbackFor = AcademyException.class)
    public void update(MarcaRequest req) throws Exception {
        log.debug("update {}", req);
        Optional<Marca> m = marcaR.findById(req.getId()); 
        
        if (m.isEmpty()) {
            throw new AcademyException("Marca non trovata in DB");
        }
        
        Marca marca = m.get();
        marca.setDescription(req.getDescription());
        marcaR.save(marca);
    }

    @Override
    @Transactional(rollbackFor = AcademyException.class)
    public void delete(Integer id) throws Exception {
        log.debug("delete {}", id);
        Optional<Marca> m = marcaR.findById(id); 
        
        if (m.isEmpty()) {
            throw new AcademyException("Marca non trovata in DB");
        }
        
        marcaR.deleteById(id);
    }

    @Override
    public List<MarcaDTO> list() {
        log.debug("listMarca");
        List<Marca> mR =  marcaR.findAll();
        return mR.stream()
                .map(m -> MarcaDTO.builder()
                        .id(m.getId())
                        .description(m.getDescription())
                        .build())
                .collect(Collectors.toList());
    }
}