package com.betacom.jpa.dto.inputs;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class MacchinaRequest extends VeicoliRequest {

    private Integer porte;
    private String targa;
    private Integer cilindrata;
}
