package com.example.hacka1.versionfinal.domain;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Restriccion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String tipoModelo;
    private String limiteUso;

    @ManyToOne
    private Empresa empresa;
}
