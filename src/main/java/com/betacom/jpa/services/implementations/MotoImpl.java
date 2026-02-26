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
import com.betacom.jpa.services.interfaces.IVeicoliServices;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RequiredArgsConstructor
@Slf4j
@Service
public class MotoImpl implements IMotoServices {

    private final IMotoRepository motoR;
    private final IVeicoliRepository veiR;
    private final IVeicoliServices veiS;

    @Override
    @Transactional(rollbackFor = AcademyException.class)
    public void create(MotoRequest req) throws Exception {
        log.debug("create {}", req);
        
        Veicoli v = veiS.create(req);
        
        Moto moto = new Moto();
        moto.setVeicoli(v);
        moto.setTarga(req.getTarga());
        moto.setCc(req.getCc());

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
        
        veiS.update(req);

        motoR.save(moto);
    }

    @Override
    @Transactional(rollbackFor = AcademyException.class)
    public void delete(Integer id) throws Exception {
        log.debug("delete {}", id);
        Optional<Moto> m = motoR.findById(id); 
        
        if(m.isEmpty()) {
            throw new AcademyException("Moto non trovata in DB");
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
                        .id(m.getVeicoli().getId())
                        .tipoVeicolo(m.getVeicoli().getTipoVeicolo())
                        .numeroRuote(m.getVeicoli().getNumeroRuote())
                        .tipoAlimentazioneId(m.getVeicoli().getTipoAlimentazione().getId())
                        .categoriaId(m.getVeicoli().getCategoria().getId())
                        .coloreId(m.getVeicoli().getColore().getId())
                        .marcaId(m.getVeicoli().getMarca().getId())
                        .annoProduzione(m.getVeicoli().getAnnoProduzione())
                        .modello(m.getVeicoli().getModello())
                        .cc(m.getCc())
                        .targa(m.getTarga())
                        .build()
                )
                .collect(Collectors.toList());
            }
}