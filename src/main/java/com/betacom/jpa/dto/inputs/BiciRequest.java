package com.betacom.jpa.dto.inputs;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class BiciRequest extends VeicoliRequest {
	
	private Integer id;	
    private Integer numeroMarce;
    private Integer idFreno;
    private Integer idSospensioni;
    private Boolean isPieghevole;

}
