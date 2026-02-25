package com.betacom.jpa.dto.outputs;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class MotoDTO {
    private Integer id;
    private String targa;
    private Integer cc;
}