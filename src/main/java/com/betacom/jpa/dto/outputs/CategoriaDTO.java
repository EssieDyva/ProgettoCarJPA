package com.betacom.jpa.dto.outputs;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CategoriaDTO {
    private Integer id;
    private String descrizione;
}