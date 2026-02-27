package com.betacom.jpa.models;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "veicoli")
public class Veicoli {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;

	@Column(name = "tipo_veicolo")
	private String tipoVeicolo;

	@Column(name = "numero_ruote")
	private Integer numeroRuote;

	@ManyToOne
	@JoinColumn(name="id_alimentazione")
	private TipoAlimentazione tipoAlimentazione;

	@ManyToOne
	@JoinColumn(name="id_categoria")
	private Categoria categoria;

	@ManyToOne
	@JoinColumn(name="id_colore")
	private Colore colore;

	@ManyToOne
	@JoinColumn(name="id_marca")
	private Marca marca;

	@Column(name="anno_produzione")
	private Integer annoProduzione;

	private String modello;

	@OneToOne(mappedBy = "veicoli",
			cascade = CascadeType.ALL,
			orphanRemoval = true)
	private Macchina macchina;

	@OneToOne(mappedBy = "veicoli",
			cascade = CascadeType.ALL,
			orphanRemoval = true)
	private Moto moto;

	@OneToOne(mappedBy = "veicoli",
			cascade = CascadeType.ALL,
			orphanRemoval = true)
	private Bici bici;
}
