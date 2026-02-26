package com.betacom.jpa.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.ToString;



@Entity
@Table (name = "sospensioni")
@Data
@ToString
public class Sospensioni {

	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column(
			name = "descrizione",
			nullable = false,
			length = 100,
			unique = true
			)
	private String description;
	@ManyToMany(mappedBy = "sospensioni")
	private List<Bici> bici;
}
