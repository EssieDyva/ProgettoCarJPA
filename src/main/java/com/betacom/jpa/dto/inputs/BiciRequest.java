package com.betacom.jpa.dto.inputs;

import com.betacom.jpa.models.Freni;
import com.betacom.jpa.models.Sospensioni;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class BiciRequest {
	
	private Integer id;	
    private Integer numeroMarce;
    private Freni idFreno;
    private Sospensioni idSospensioni;
    private Boolean isPieghevole;

}
