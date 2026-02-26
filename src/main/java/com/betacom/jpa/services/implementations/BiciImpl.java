package com.betacom.jpa.services.implementations;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.betacom.jpa.dto.inputs.BiciRequest;
import com.betacom.jpa.dto.outputs.BiciDTO;
import com.betacom.jpa.exception.AcademyException;
import com.betacom.jpa.models.Bici;
import com.betacom.jpa.models.Veicoli;
import com.betacom.jpa.repository.IBiciRepository;
import com.betacom.jpa.repository.IVeicoliRepository;
import com.betacom.jpa.services.interfaces.IBiciServices;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RequiredArgsConstructor
@Slf4j
@Service
public class BiciImpl implements IBiciServices  {

	private final IBiciRepository bicR;
	private final IVeicoliRepository veiR;
	
	@Transactional (rollbackFor = Exception.class)
	@Override
	public void create(BiciRequest req) throws Exception {
		log.debug("create []", req);
		Veicoli v = veiR.findById(req.getId())
                .orElseThrow(() -> new AcademyException("Veicolo non trovato"));
		Bici bici = new Bici();
		bici.setId(v.getId());
		bici.setIdFreno(req.getIdFreno());
		bici.setIdSospensioni(req.getIdSospensioni());
		bici.setIsPieghevole(req.getIsPieghevole());
		bici.setNumeroMarce(req.getNumeroMarce());
		bici.setVeicoli(v);
		
		bicR.save(bici);
		
	}

	@Transactional (rollbackFor = Exception.class)
	@Override
	public void update(BiciRequest req) throws Exception {
		log.debug("update {}", req);
		Bici bici = bicR.findById(req.getId())
				.orElseThrow(() -> new AcademyException("Bici non trovata"));
		bici.setIdFreno(req.getIdFreno());
		bici.setIdSospensioni(req.getIdSospensioni());
		bici.setIsPieghevole(req.getIsPieghevole());
		bici.setNumeroMarce(req.getNumeroMarce());
		
		bicR.save(bici);
	}

	@Transactional (rollbackFor = Exception.class)
	@Override
	public void delete(Integer id) throws Exception {
		log.debug("delete {}", id);
		Bici bici = bicR.findById(id)
				.orElseThrow(() -> new AcademyException("Bici non trovata"));
		
		bicR.delete(bici);
	}

	@Override
	public List<BiciDTO> list() {
		log.debug("list {}");
		List<Bici> lB = bicR.findAll();
		return lB.stream()
				.map(b -> BiciDTO.builder()
						.id(b.getId())
						.idFreno(b.getIdFreno())
						.idSospensioni(b.getIdSospensioni())
						.isPieghevole(b.getIsPieghevole())
						.numeroMarce(b.getNumeroMarce())
						.build())
				.collect(Collectors.toList());	
	}

}
