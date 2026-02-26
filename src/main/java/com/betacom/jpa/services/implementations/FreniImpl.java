package com.betacom.jpa.services.implementations;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.betacom.jpa.dto.inputs.FreniRequest;
import com.betacom.jpa.dto.outputs.FreniDTO;
import com.betacom.jpa.exception.AcademyException;
import com.betacom.jpa.models.Freni;
import com.betacom.jpa.repository.IFreniRepository;
import com.betacom.jpa.services.interfaces.IFreniServices;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class FreniImpl implements IFreniServices {

	private final IFreniRepository freniR;

	@Override
	public List<FreniDTO> listFreni() {
		log.debug("listFreni");
		List<Freni> lF = freniR.findAll();
		return lF.stream()
				.map(f -> FreniDTO.builder()
						.id(f.getId())
						.description(f.getDescription())
						.build())
				.collect(Collectors.toList());
	}

	@Override
	@Transactional(rollbackFor = AcademyException.class)
	public void create(FreniRequest req) throws Exception {
		log.debug("create {}", req);

        Boolean exist = freniR.findByDescription(req.getDescription());
		
		if (exist != null) 
			throw new AcademyException("Freno già presente in DB");

		Freni freni = new Freni();
		freni.setDescription(req.getDescription());
		freniR.save(freni);
	}

	@Override
	@Transactional(rollbackFor = AcademyException.class)
	public void update(FreniRequest req) throws Exception {
		log.debug("update {}", req);

        Boolean exist = freniR.findByDescription(req.getDescription());
		
		if (exist != null) 
			throw new AcademyException("Freno già presente in DB");

		Freni freni = freniR.findById(req.getId())
				.orElseThrow(() -> new AcademyException("Freni non trovati"));
		if (req.getDescription() != null) {
			freni.setDescription(req.getDescription());
		}
		freniR.save(freni);
	}

	@Override
	@Transactional(rollbackFor = AcademyException.class)
	public void delete(Integer id) throws Exception {
		log.debug("delete {}", id);
		Freni freni = freniR.findById(id)
				.orElseThrow(() -> new AcademyException("Freni non trovati"));
		freniR.delete(freni);
	}

}
