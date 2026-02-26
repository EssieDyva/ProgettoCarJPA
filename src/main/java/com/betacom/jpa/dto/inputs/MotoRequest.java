package com.betacom.jpa.dto.inputs;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class MotoRequest {
    private Integer id;
    private String targa;
    private Integer cc;
    
}