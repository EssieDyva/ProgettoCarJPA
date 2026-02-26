package com.betacom.jpa.services.implementations;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.betacom.jpa.dto.inputs.VeicoliRequest;
import com.betacom.jpa.dto.outputs.MacchinaDTO;
import com.betacom.jpa.dto.outputs.VeicoliDTO;
import com.betacom.jpa.exception.AcademyException;
import com.betacom.jpa.models.*;
import com.betacom.jpa.repository.*;
import com.betacom.jpa.services.interfaces.IVeicoliServices;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RequiredArgsConstructor
@Slf4j
@Service
public class VeicoliImpl implements IVeicoliServices {

    private final IMacchinaRepository IMacchinaRepository;

    private final IVeicoliRepository veiR;
    private final ICategoriaRepository catR;
    private final ITipoAlimentazioneRepository taR;
    private final IColoreRepository colR;
    private final IMarcaRepository marR;

    VeicoliImpl(IMacchinaRepository IMacchinaRepository) {
        this.IMacchinaRepository = IMacchinaRepository;
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public Veicoli create(VeicoliRequest req) throws Exception {
        log.debug("create {}", req);

        validateIds(req);

        Veicoli vei = new Veicoli();
        vei.setTipoVeicolo(req.getTipoVeicolo());
        vei.setNumeroRuote(req.getNumeroRuote());
        vei.setTipoAlimentazione(taR.findById(req.getTipoAlimentazioneId()).get());
        vei.setCategoria(catR.findById(req.getCategoriaId()).get());
        vei.setColore(colR.findById(req.getColoreId()).get());
        vei.setMarca(marR.findById(req.getMarcaId()).get());
        vei.setAnnoProduzione(req.getAnnoProduzione());
        vei.setModello(req.getModello());

        vei = veiR.save(vei);
        req.setId(vei.getId());

        return vei;
    }

    @Transactional (rollbackFor = Exception.class)
    @Override
    public void update(VeicoliRequest req) throws Exception {
        log.debug("update {}", req);

        Optional<Veicoli> vei = veiR.findById(req.getId());

        if (vei.isEmpty())
            throw new AcademyException("Veicolo non presente in DB");

        validateIds(req);

        Veicoli v = vei.get();
        v.setTipoVeicolo(req.getTipoVeicolo());
        v.setNumeroRuote(req.getNumeroRuote());
        v.setTipoAlimentazione(taR.findById(req.getTipoAlimentazioneId()).get());
        v.setCategoria(catR.findById(req.getCategoriaId()).get());
        v.setColore(colR.findById(req.getColoreId()).get());
        v.setMarca(marR.findById(req.getMarcaId()).get());
        v.setAnnoProduzione(req.getAnnoProduzione());
        v.setModello(req.getModello());

        veiR.save(v);
    }

    @Transactional (rollbackFor = Exception.class)
    @Override
    public void delete(Integer id) throws Exception {
        log.debug("delete {}", id);

        Optional<Veicoli> vei = veiR.findById(id);

        if (vei.isEmpty())
            throw new AcademyException("Veicolo non presente in DB");

        veiR.delete(vei.get());
    }

    @Override
    public List<VeicoliDTO> list() throws Exception {
        log.debug("list");
        List<Veicoli> lV = veiR.findAll();
        return lV.stream()
                .map(v -> VeicoliDTO.builder()
                        .id(v.getId())
                        .tipoVeicolo(v.getTipoVeicolo())
                        .numeroRuote(v.getNumeroRuote())
                        .tipoAlimentazioneId(v.getTipoAlimentazione().getId())
                        .categoriaId(v.getCategoria().getId())
                        .coloreId(v.getColore().getId())
                        .marcaId(v.getMarca().getId())
                        .annoProduzione(v.getAnnoProduzione())
                        .modello(v.getModello())
                        .build())
                .collect(Collectors.toList());
    }

    private void validateIds(VeicoliRequest req) throws AcademyException {

        if (taR.findById(req.getTipoAlimentazioneId()).isEmpty()
                || catR.findById(req.getCategoriaId()).isEmpty()
                || colR.findById(req.getColoreId()).isEmpty()
                || marR.findById(req.getMarcaId()).isEmpty()) {

            throw new AcademyException("Un ID inserito non esiste nella corrispettiva tabella");
        }
    }

	@Override
	public List<VeicoliDTO> selectAll(Integer id, String tipoVeicolo, Integer numeroRuote, String tipoAlimentazione, String categoria, String colore, String marca, Integer annoProduzione, String modello
									 ,Integer porte, String targaMac, Integer cilindrata
									 ,String targaMo, Integer cc
									 ,Integer numeroMarce, String freno, String sospensioni, Boolean isPieghevole ) throws Exception {
		
		log.debug("select {} / {} / {} / {} / {} / {} / {} / {} / {} / {} / {} / {} / {} / {} / {} / {} / {} / {}",
				 id, tipoVeicolo, numeroRuote, tipoAlimentazione, categoria, colore, marca, annoProduzione, modello
				 , porte, targaMac, cilindrata
				 , targaMo,  cc
				 , numeroMarce,  freno,  sospensioni,  isPieghevole);	
		
		List<Veicoli> lV = veiR.selectAll(id, tipoVeicolo, numeroRuote, tipoAlimentazione, categoria, colore, marca, annoProduzione, modello
				 , porte, targaMac, cilindrata
				 , targaMo,  cc
				 , numeroMarce,  freno,  sospensioni,  isPieghevole);
		
		return buildVeicoloDTO(lV);
	}

	private List<VeicoliDTO> buildVeicoloDTO(List<Veicoli> lV) {
		return lV.stream().map(v -> VeicoliDTO.builder()
			.id(v.getId())
			.tipoVeicolo(v.getTipoVeicolo())
			.numeroRuote(v.getNumeroRuote())
			.tipoAlimentazione(v.getTipoAlimentazione())
			.categoria(v.getCategoria())
			.colore(v.getColore())
			.marca(v.getMarca())
			.annoProduzione(v.getAnnoProduzione())
			.modello(v.getModello())
			
			
			.macchina((v.getMacchina() == null) ? null : MacchinaDTO.builder()
					.porte(v.getMacchina.getPorte())
					.targa(v.getMacchina().getTarga())
					.cilindrata(v.getMacchina().getCilindrata())
					).build())
				
			.moto((v.getMoto() == null) ? null : MotoDTO.builder()
					
					
					
					
					)
			
		}
		
		
	}
	
//	private List<VeicoliDTO> buildVeicoloDTO(List<Veicoli> lV) {
//	    return lV.stream().map(v -> {
//	        // Build base (Campi comuni a tutti)
//	        VeicoliDTO.VeicoliDTOBuilder builder = VeicoliDTO.builder()
//	            .id(v.getId())
//	            .tipoVeicolo(v.getTipoVeicolo() != null ? v.getTipoVeicolo() : null)
//	            .numeroRuote(v.getNumeroRuote())
//	            .tipoAlimentazione(v.getTipoAlimentazione() != null ? v.getTipoAlimentazione() : null)
//	            .categoria(v.getCategoria() != null ? v.getCategoria() : null)
//	            .colore(v.getColore() != null ? v.getColore() : null)
//	            .marca(v.getMarca() != null ? v.getMarca() : null)
//	            .annoProduzione(v.getAnnoProduzione())
//	            .modello(v.getModello());
//
//	        // Dati specifici Macchina
//	        if (v.getMacchina() != null) {
//	            builder.porte(v.getMacchina().getPorte())
//	                   .targaMac(v.getMacchina().getTarga())
//	                   .cilindrata(v.getMacchina().getCilindrata());
//	        }
//	        // Dati specifici Moto
//	        if (v.getMoto() != null) {
//	            builder.targaMo(v.getMoto().getTarga())
//	                   .cc(v.getMoto().getCc());
//	        }
//	        // Dati specifici Bici
//	        if (v.getBici() != null) {
//	            builder.numeroMarce(v.getBici().getNumeroMarce())
//	                   .isPieghevole(v.getBici().getIsPieghevole())
//	                   .freno(v.getBici().getFreni() != null ? ((Freni) v.getBici().getFreni()).getDescription() : null)
//	                   .sospensioni(v.getBici().getSospensioni() != null ? ((Sospensioni) v.getBici().getSospensioni()).getDescription() : null);
//	        }
//
//	        return builder.build();
//	    }).toList();
//	}


}
