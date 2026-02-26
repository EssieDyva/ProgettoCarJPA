package com.betacom.jpa.services.implementations;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.betacom.jpa.dto.inputs.CategoriaRequest;
import com.betacom.jpa.dto.outputs.CategoriaDTO;
import com.betacom.jpa.exception.AcademyException;
import com.betacom.jpa.models.Categoria;
import com.betacom.jpa.repository.ICategoriaRepository;
import com.betacom.jpa.services.interfaces.ICategoriaServices;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class CategoriaImpl implements ICategoriaServices {

    private final ICategoriaRepository categoriaR;

    @Override
    @Transactional(rollbackFor = AcademyException.class)
    public Integer create(CategoriaRequest req) throws AcademyException {
    	log.debug("create {}", req);
    	
        Boolean exist = categoriaR.findByDescrizione(req.getDescrizione());
		
		if (exist != null)
			throw new AcademyException("Categoria già presente in DB");

        Categoria cat = new Categoria();
        cat.setDescrizione(req.getDescrizione());
        return categoriaR.save(cat).getId();
    }

    @Override
    @Transactional(rollbackFor = AcademyException.class)
    public void update(CategoriaRequest req) throws AcademyException {
    	log.debug("update {}", req);
        Optional<Categoria> cat = categoriaR.findById(req.getId());
        if (cat.isEmpty()) {
            throw new AcademyException("Categoria non trovata");
        }
        Categoria c = cat.get();
        if (req.getDescrizione() != null) {
            c.setDescrizione(req.getDescrizione());
        }
        categoriaR.save(c);
    }

    @Override
    @Transactional(rollbackFor = AcademyException.class)
    public void delete(Integer id) throws AcademyException {
    	log.debug("delete {}", id);
        categoriaR.deleteById(id);
    }

    @Override
    public List<CategoriaDTO> findAll(){
    	log.debug("findAll");
        return categoriaR.findAll().stream() 
                .map(c -> CategoriaDTO.builder()
                        .id(c.getId())
                        .descrizione(c.getDescrizione())
                        .build())
                .collect(Collectors.toList());
    }
}