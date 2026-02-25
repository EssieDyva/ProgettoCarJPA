package com.betacom.jpa.services.implementations;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.betacom.jpa.dto.inputs.MotoRequest;
import com.betacom.jpa.dto.outputs.MotoDTO;
import com.betacom.jpa.exception.AcademyException;
import com.betacom.jpa.models.Moto;
import com.betacom.jpa.models.Veicoli;
import com.betacom.jpa.repository.IMotoRepository;
import com.betacom.jpa.repository.IVeicoliRepository;
import com.betacom.jpa.services.interfaces.IMotoServices;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RequiredArgsConstructor
@Slf4j
@Service
public class MotoImpl implements IMotoServices {

    private final IMotoRepository motoR;
    private final IVeicoliRepository veiR;

    @Override
    @Transactional(rollbackFor = AcademyException.class)
    public void create(MotoRequest req) throws Exception {
        log.debug("create {}", req);
        Veicoli v = veiR.findById(req.getId())
                .orElseThrow(() -> new AcademyException("Veicolo non trovato"));
        Moto moto = new Moto();
        moto.setId(v.getId());
        moto.setTarga(req.getTarga());
        moto.setCc(req.getCc());
        moto.setVeicoli(v);

        motoR.save(moto);
    }

    @Override
    @Transactional(rollbackFor = AcademyException.class)
    public void update(MotoRequest req) throws Exception {
        log.debug("update {}", req);
        Moto moto = motoR.findById(req.getId())
                .orElseThrow(() -> new AcademyException("Moto non trovata"));
        moto.setTarga(req.getTarga());
        moto.setCc(req.getCc());
        motoR.save(moto);
    }

    @Override
    @Transactional(rollbackFor = AcademyException.class)
    public void delete(Integer id) throws Exception {
        log.debug("delete {}", id);
        Optional<Moto> m = motoR.findById(id); 
        
        if(m.isEmpty()) {
            throw new AcademyException("Marca non trovata in DB");
        }
        
        motoR.deleteById(id);
        veiR.deleteById(id);
    }

    @Override
    public List<MotoDTO> list() {
        log.debug("listMoto");
        List<Moto> lM = motoR.findAll();
        return lM.stream()
                .map(m -> MotoDTO.builder()
                        .id(m.getId())
                        .targa(m.getTarga())
                        .cc(m.getCc())
                        .build())
                .collect(Collectors.toList());
    }
}