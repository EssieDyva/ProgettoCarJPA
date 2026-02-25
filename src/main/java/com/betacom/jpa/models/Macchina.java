package com.betacom.jpa.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.MapsId;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "macchina")
public class Macchina {

    @Id
    @Column(name = "id_veicolo")
    private Integer id;

    @OneToOne
    @MapsId
    @JoinColumn(name = "id_veicolo")
    private Veicoli veicoli;

    @Column(name = "numero_porte")
    private Integer numeroporte;

    @Column(name = "capacita_bagagliaio")
    private Integer capacitaBagagliaio;

    @Column(name = "potenza_cv")
    private Integer potenzaCv;
}
