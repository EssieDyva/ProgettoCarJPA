package com.betacom.jpa.services.implementations;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.betacom.jpa.dto.inputs.VeicoliRequest;
import com.betacom.jpa.dto.outputs.MacchinaDTO;
import com.betacom.jpa.dto.outputs.MotoDTO;
import com.betacom.jpa.dto.outputs.BiciDTO;
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

    private final IVeicoliRepository veiR;
    private final ICategoriaRepository catR;
    private final ITipoAlimentazioneRepository taR;
    private final IColoreRepository colR;
    private final IMarcaRepository marR;

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
	public List<VeicoliDTO> selectAll() throws Exception {
		log.debug("select ");	
		
		List<Veicoli> lV = veiR.selectAll();
		
		return buildVeicoloDTO(lV);
	}

    @Override
    public List<VeicoliDTO> selectByFilter(Integer id, String colore, String categoria, Integer annoProduzione) throws Exception {
        log.debug("selectByFilter {} / {} / {} / {} ", id, colore, categoria, annoProduzione);	

		List<Veicoli> lV = veiR.selectByFilter(id, colore, categoria, annoProduzione);
		
		return buildVeicoloDTO(lV);
    }

	private List<VeicoliDTO> buildVeicoloDTO(List<Veicoli> lV) {
	    return lV.stream().<VeicoliDTO>map(v -> VeicoliDTO.builder()  
	            .id(v.getId())
	            .tipoVeicolo(v.getTipoVeicolo())
	            .numeroRuote(v.getNumeroRuote())
	            .tipoAlimentazioneId(v.getTipoAlimentazione().getId())
	            .categoriaId(v.getCategoria().getId())
	            .coloreId(v.getColore().getId())
	            .marcaId(v.getMarca().getId())
	            .annoProduzione(v.getAnnoProduzione())
	            .modello(v.getModello())
	            
	            .macchina(v.getMacchina() == null ? null : MacchinaDTO.builder()
	                .porte(v.getMacchina().getPorte())
	                .targa(v.getMacchina().getTarga())
	                .cilindrata(v.getMacchina().getCilindrata())
	                .build())
	                
	            .moto(v.getMoto() == null ? null : MotoDTO.builder()
	                .targa(v.getMoto().getTarga())
	                .cc(v.getMoto().getCc())
	                .build())

	            .bici(v.getBici() == null ? null : BiciDTO.builder()
	                .numeroMarce(v.getBici().getNumeroMarce())
	                .idFreno(v.getBici().getIdFreno())
	                .idSospensioni(v.getBici().getIdSospensioni())
	                .isPieghevole(v.getBici().getIsPieghevole())
	                .build())

	            .build() 
	        )
	        .toList();
	}

    
	
}
