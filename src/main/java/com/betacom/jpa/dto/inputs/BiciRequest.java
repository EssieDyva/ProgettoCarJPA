package com.betacom.jpa.dto.inputs;

import com.betacom.jpa.models.Freni;
import com.betacom.jpa.models.Sospensioni;
import com.betacom.jpa.models.Veicoli;

import jakarta.persistence.Column;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
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
