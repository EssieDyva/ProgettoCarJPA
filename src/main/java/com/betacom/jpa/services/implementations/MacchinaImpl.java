package com.betacom.jpa.services.implementations;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.betacom.jpa.exception.AcademyException;
import com.betacom.jpa.dto.inputs.MacchinaRequest;
import com.betacom.jpa.dto.inputs.MarcaRequest;
import com.betacom.jpa.dto.outputs.MacchinaDTO;
import com.betacom.jpa.dto.outputs.MarcaDTO;
import com.betacom.jpa.models.Macchina;
import com.betacom.jpa.models.Marca;
import com.betacom.jpa.models.Veicoli;
import com.betacom.jpa.repository.IMacchinaRepository;
import com.betacom.jpa.repository.IMarcaRepository;
import com.betacom.jpa.repository.IVeicoliRepository;
import com.betacom.jpa.services.interfaces.IMacchinaServices;
import com.betacom.jpa.services.interfaces.IMarcaServices;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class MacchinaImpl implements IMacchinaServices {

    @Autowired
    private IMacchinaRepository maccR;
    private IVeicoliRepository veiR;

    @Override
    @Transactional(rollbackFor = AcademyException.class)
    public void create(MacchinaRequest req) throws Exception {
    	log.debug("create {}", req);

        Veicoli v=veiR.findById(req.getId())
        		.orElseThrow(()-> new AcademyException("veicolo non trovato"));

        Macchina mac = new Macchina();
        
        mac.setPorte(req.getPorte());
        mac.setTarga(req.getTarga());
        mac.setCilindrata(req.getCilindrata());
       

        maccR.save(mac);
    }

    @Override
    @Transactional(rollbackFor = AcademyException.class)
    public void update(MacchinaRequest req) throws Exception {
        log.debug("update {}", req);
        Optional<Macchina> m = maccR.findById(req.getId()); 
        if (m.isEmpty()) {
            throw new AcademyException("Macchina non trovata in DB");
        }
        Macchina macchina = m.get();
        macchina.setPorte(req.getPorte());
        macchina.setTarga(req.getTarga());
        macchina.setCilindrata(req.getCilindrata());
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