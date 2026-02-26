package com.betacom.jpa.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Data
@Entity
@Table(name = "bici")
public class Bici {
	
	@Id
	@Column(name = "id_veicolo")
	private Integer id;
	
    @OneToOne
    @MapsId
    @JoinColumn(name = "id_veicolo")
    private Veicoli veicoli;
	
    @Column(name = "numero_marce")
    private Integer numeroMarce;
    
    @ManyToOne
    @JoinColumn(name="id_freno")
    private Freni idFreno;
    
    @ManyToOne
    @JoinColumn(name="id_sospensione")
    private Sospensioni idSospensioni;
    
    @Column(name = "is_pieghevole")
    private Boolean isPieghevole;

}
