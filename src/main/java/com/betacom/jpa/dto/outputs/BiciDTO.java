package com.betacom.jpa.dto.outputs;

import com.betacom.jpa.models.Freni;
import com.betacom.jpa.models.Sospensioni;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@Builder
@ToString
public class BiciDTO {
	private Integer id;	
    private Integer numeroMarce;
    private Freni idFreno;
    private Sospensioni idSospensioni;
    private Boolean isPieghevole;

}
