package com.betacom.jpa.dto.inputs;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class VeicoliRequest {

    private Integer id;
    private String tipoVeicolo;
    private String numeroRuote;
    private Integer tipoAlimentazioneId;
    private Integer categoriaId;
    private Integer coloreId;
    private Integer marcaId;
    private Integer annoProduzione;
    private String modello;
}
