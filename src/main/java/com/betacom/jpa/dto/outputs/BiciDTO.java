package com.betacom.jpa.dto.outputs;

import com.betacom.jpa.models.Freni;
import com.betacom.jpa.models.Sospensioni;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

@Setter
@Getter
@SuperBuilder
@ToString
public class BiciDTO extends VeicoliDTO{
	private Integer id;	
    private Integer numeroMarce;
    private Freni idFreno;
    private Sospensioni idSospensioni;
    private Boolean isPieghevole;

}
