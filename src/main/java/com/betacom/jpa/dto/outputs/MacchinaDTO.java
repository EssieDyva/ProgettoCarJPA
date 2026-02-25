package com.betacom.jpa.dto.outputs;

import lombok.Builder;
import lombok.Data;
import lombok.ToString;

@Data
@Builder
@ToString
public class MacchinaDTO {

	private Integer id;
	private Integer porte;
	private String targa;
	private Integer cilindrata;
}
