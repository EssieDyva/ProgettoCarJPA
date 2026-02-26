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
import com.betacom.jpa.repository.IFreniRepository;
import com.betacom.jpa.repository.ISospensioniRepository;
import com.betacom.jpa.repository.IVeicoliRepository;
import com.betacom.jpa.services.interfaces.IBiciServices;
import com.betacom.jpa.services.interfaces.IVeicoliServices;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RequiredArgsConstructor
@Slf4j
@Service
public class BiciImpl implements IBiciServices {
	private final IBiciRepository bicR;
	private final IVeicoliRepository veiR;
	private final IFreniRepository frenR;
	private final ISospensioniRepository sospR;
	private final IVeicoliServices veiS;

	@Transactional(rollbackFor = Exception.class)
	@Override
	public void create(BiciRequest req) throws Exception {
		log.debug("create {}", req);

		ValidateBiciIds(req);
		
		veiS.create(req);
		Veicoli v = veiR.findById(req.getId())
				.orElseThrow(() -> new AcademyException("veicolo non trovato dopo creazione"));

		Bici bici = new Bici();
		bici.setVeicoli(v);
		bici.setIdFreno(frenR.findById(req.getIdFreno()).get());
		bici.setIdSospensioni(sospR.findById(req.getIdSospensioni()).get());
		bici.setIsPieghevole(req.getIsPieghevole());
		bici.setNumeroMarce(req.getNumeroMarce());

		bicR.save(bici);
	}

	@Transactional(rollbackFor = Exception.class)
	@Override
	public void update(BiciRequest req) throws Exception {
		log.debug("update {}", req);
		Bici bici = bicR.findById(req.getId())
				.orElseThrow(() -> new AcademyException("Bici non trovata"));

		ValidateBiciIds(req);
		
		bici.setIdFreno(frenR.findById(req.getIdFreno()).get());
		bici.setIdSospensioni(sospR.findById(req.getIdSospensioni()).get());
		bici.setIsPieghevole(req.getIsPieghevole());
		bici.setNumeroMarce(req.getNumeroMarce());

		veiS.update(req);

		bicR.save(bici);
	}

	@Transactional(rollbackFor = Exception.class)
	@Override
	public void delete(Integer id) throws Exception {
		log.debug("delete {}", id);
		Bici bici = bicR.findById(id)
				.orElseThrow(() -> new AcademyException("Bici non trovata"));
		bicR.deleteById(id);
		veiR.deleteById(id);
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

	private void ValidateBiciIds(BiciRequest req) throws AcademyException {
		if (frenR.findById(req.getIdFreno()).isEmpty()
				|| sospR.findById(req.getIdSospensioni()).isEmpty()) {

			throw new AcademyException("Freno o Sospensioni non trovati in DB");
		}
	
	}

}
