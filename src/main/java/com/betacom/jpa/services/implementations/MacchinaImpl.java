package com.betacom.jpa.services.implementations;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;

import com.betacom.jpa.dto.inputs.MacchinaRequest;
import com.betacom.jpa.dto.outputs.MacchinaDTO;
import com.betacom.jpa.exception.AcademyException;
import com.betacom.jpa.models.Macchina;
import com.betacom.jpa.models.Veicoli;
import com.betacom.jpa.repository.IMacchinaRepository;
import com.betacom.jpa.repository.IVeicoliRepository;
import com.betacom.jpa.repository.ICategoriaRepository;
import com.betacom.jpa.repository.IColoreRepository;
import com.betacom.jpa.repository.IMarcaRepository;
import com.betacom.jpa.repository.ITipoAlimentazioneRepository;
import com.betacom.jpa.services.interfaces.IVeicoliServices;
import com.betacom.jpa.services.interfaces.IMacchinaServices;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class MacchinaImpl implements IMacchinaServices {

    private final IMacchinaRepository maccR;
    private final IVeicoliRepository veiR;
    private final ICategoriaRepository catR;
    private final ITipoAlimentazioneRepository taR;
    private final IColoreRepository colR;
    private final IMarcaRepository marR;
    private final IVeicoliServices veiS;

    @Override
    @Transactional(rollbackFor = AcademyException.class)
    public void create(MacchinaRequest req) throws Exception {
        log.debug("create {}", req);

        Veicoli v = veiS.create(req);

        Macchina mac = new Macchina();
        mac.setVeicoli(v);
        mac.setPorte(req.getPorte());
        mac.setTarga(req.getTarga());
        mac.setCilindrata(req.getCilindrata());

        maccR.save(mac);
    }

    @Override
    @Transactional(rollbackFor = AcademyException.class)
    public void update(MacchinaRequest req) throws Exception {
        log.debug("update {}", req);
        Macchina macchina = maccR.findById(req.getId())
                .orElseThrow(() -> new AcademyException("Macchina non trovata in DB"));

        macchina.setPorte(req.getPorte());
        macchina.setTarga(req.getTarga());
        macchina.setCilindrata(req.getCilindrata());

        Veicoli v = macchina.getVeicoli();
        if (v != null) {
            v.setTipoVeicolo(req.getTipoVeicolo());
            v.setNumeroRuote(req.getNumeroRuote());
            if (req.getTipoAlimentazioneId() != null) {
                v.setTipoAlimentazione(taR.findById(req.getTipoAlimentazioneId())
                        .orElseThrow(() -> new AcademyException("Tipo alimentazione non trovato")));
            }
            if (req.getCategoriaId() != null) {
                v.setCategoria(catR.findById(req.getCategoriaId())
                        .orElseThrow(() -> new AcademyException("Categoria non trovata")));
            }
            if (req.getColoreId() != null) {
                v.setColore(colR.findById(req.getColoreId())
                        .orElseThrow(() -> new AcademyException("Colore non trovato")));
            }
            if (req.getMarcaId() != null) {
                v.setMarca(marR.findById(req.getMarcaId())
                        .orElseThrow(() -> new AcademyException("Marca non trovata")));
            }
            v.setAnnoProduzione(req.getAnnoProduzione());
            v.setModello(req.getModello());
            veiR.save(v);
        }

        maccR.save(macchina);
    }

    @Override
    @Transactional(rollbackFor = AcademyException.class)
    public void delete(Integer id) throws Exception {
        log.debug("delete {}", id);
        Optional<Macchina> m = maccR.findById(id); 
        
        if (m.isEmpty()) {
            throw new AcademyException("Macchina non trovata");
        }
        
        maccR.deleteById(id);
        veiR.deleteById(id);
    }

    @Override
    public List<MacchinaDTO> list() {
        log.debug("listMacchine");
        List<Macchina> lm =  maccR.findAll();
        return lm.stream()
                .map(m -> MacchinaDTO.builder()
                        .id(m.getId())
                        .porte(m.getPorte())
                        .cilindrata(m.getCilindrata())
                        .build())
                .collect(Collectors.toList());
    }
}