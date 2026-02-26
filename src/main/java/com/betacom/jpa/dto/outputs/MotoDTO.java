package com.betacom.jpa.dto.outputs;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@SuperBuilder
@ToString(callSuper = true)
public class MotoDTO extends VeicoliDTO {
    private Integer id;
    private String targa;
    private Integer cc;
}