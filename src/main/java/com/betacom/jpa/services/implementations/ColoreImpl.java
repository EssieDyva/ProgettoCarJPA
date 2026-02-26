package com.betacom.jpa.services.implementations;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.betacom.jpa.dto.inputs.ColoreRequest;
import com.betacom.jpa.dto.outputs.ColoreDTO;
import com.betacom.jpa.exception.AcademyException;
import com.betacom.jpa.models.Colore;
import com.betacom.jpa.repository.IColoreRepository;
import com.betacom.jpa.services.interfaces.IColoreServices;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RequiredArgsConstructor
@Slf4j
@Service
public class ColoreImpl implements IColoreServices {

	private final IColoreRepository colR;
	
	@Transactional (rollbackFor = AcademyException.class)
	@Override
	public void create(ColoreRequest req) throws Exception {
		log.debug("create {}", req);
		Boolean exists = colR.findByDescription(req.getDescription());
		
		if(exists != null)
			throw new AcademyException("Colore presente in db:" + req.getDescription());
		
		Colore co = new Colore();
		co.setDescription(req.getDescription());
		
		colR.save(co);
	}
	
	@Transactional (rollbackFor = AcademyException.class)
	@Override
	public void update(ColoreRequest req) throws Exception {
		log.debug("update {}", req);
		Colore co = colR.findById(req.getId())
				.orElseThrow(() -> new AcademyException("Colore non trovato"));
		if (req.getDescription() != null) {
			if (colR.findByDescription(req.getDescription().trim().toUpperCase())) {
				throw new AcademyException("Colore presente in db:" + req.getDescription());		
			}
			co.setDescription(req.getDescription().trim().toUpperCase());
		}
		colR.save(co);
	}

	@Transactional (rollbackFor = AcademyException.class)
	@Override
	public void delete(Integer id) throws Exception {
		log.debug("delete {}", id);
		Colore co = colR.findById(id)
				.orElseThrow(() -> new AcademyException("Colore non trovato"));
		
		colR.delete(co);
	}
	

	@Override
	public List<ColoreDTO> list() throws Exception {
		log.debug("list {}");
		List<Colore> lC = colR.findAll();
		return lC.stream()
				.map(c -> ColoreDTO.builder()
						.id(c.getId())
						.description(c.getDescription())
						.build())
				.collect(Collectors.toList());			
	}
}
