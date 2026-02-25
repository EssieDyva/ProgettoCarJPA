package com.betacom.jpa.dto.inputs;
import lombok.Builder;
import lombok.Data;
@Data
@Builder
public class CategoriaRequest {
private Integer id;
private String descrizione;
}

