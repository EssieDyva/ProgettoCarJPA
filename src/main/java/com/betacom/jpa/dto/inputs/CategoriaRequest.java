package com.betacom.jpa.dto.inputs;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class CategoriaRequest {
    private Integer id;
    private String descrizione;
}
