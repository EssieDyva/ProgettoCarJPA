package com.betacom.jpa.dto.outputs;

import lombok.Builder;
import lombok.Data;
import lombok.ToString;

@Data
@Builder
@ToString
public class VeicoliDTO {

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
