package com.betacom.jpa.dto.outputs;

import lombok.Data;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
@ToString
public class VeicoliDTO {

    private Integer id;
    private String tipoVeicolo;
    private Integer numeroRuote;
    private Integer tipoAlimentazioneId;
    private Integer categoriaId;
    private Integer coloreId;
    private Integer marcaId;
    private Integer annoProduzione;
    private String modello;
}
