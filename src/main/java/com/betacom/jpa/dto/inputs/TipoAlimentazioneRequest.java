package com.betacom.jpa.dto.inputs;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class TipoAlimentazioneRequest {

	private Integer id;
	private String description;
}
