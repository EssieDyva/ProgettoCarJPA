package com.betacom.jpa.services.implementations;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.betacom.jpa.dto.inputs.TipoAlimentazioneRequest;
import com.betacom.jpa.dto.outputs.TipoAlimentazioneDTO;
import com.betacom.jpa.exception.AcademyException;
import com.betacom.jpa.models.TipoAlimentazione;
import com.betacom.jpa.repository.ITipoAlimentazioneRepository;
import com.betacom.jpa.services.interfaces.ITipoAlimentazioneServices;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RequiredArgsConstructor
@Slf4j
@Service
public class TipoAlimentazioneImpl implements ITipoAlimentazioneServices {
	
	private final ITipoAlimentazioneRepository taR;
	
	@Transactional (rollbackFor = Exception.class)
	@Override
	public void create(TipoAlimentazioneRequest req) throws Exception {
		log.debug("create {}", req);

		Boolean exist = taR.findByDescription(req.getDescription());
		
		if (exist != null) 
			throw new AcademyException("Tipo alimentazione già presente in DB");
		
		TipoAlimentazione tipoA = new TipoAlimentazione();
		tipoA.setDescription(req.getDescription());
		
		taR.save(tipoA);
		
	}

	@Transactional (rollbackFor = Exception.class)
	@Override
	public void update(TipoAlimentazioneRequest req) throws Exception {
		log.debug("update {}", req);
		Optional<TipoAlimentazione> tipoA = taR.findById(req.getId());
		if (tipoA.isEmpty())
			throw new AcademyException("Tipo alimentazione non trovato in DB");
		
		Boolean exist = taR.findByDescription(req.getDescription());
		if (exist != null) 
			throw new AcademyException("Tipo alimentazione già presente in DB");
		
		TipoAlimentazione t = tipoA.get();
		if(req.getDescription() != null)
			t.setDescription(req.getDescription());
		
		taR.save(t);
	}

	@Transactional (rollbackFor = Exception.class)
	@Override
	public void delete(Integer id) throws Exception {
		log.debug("delete {}", id);
		Optional<TipoAlimentazione> tipoA = taR.findById(id);
		if (tipoA.isEmpty())
			throw new AcademyException("Tipo alimentazione non trovato in DB");
		
		taR.delete(tipoA.get());
	}

	@Override
	public List<TipoAlimentazioneDTO> list() throws Exception {
		log.debug("list");
		List<TipoAlimentazione> lT = taR.findAll();
		return lT.stream()
				.map(t -> TipoAlimentazioneDTO.builder()
						.id(t.getId())
						.description(t.getDescription())
						.build()
						).toList();
	}

}
