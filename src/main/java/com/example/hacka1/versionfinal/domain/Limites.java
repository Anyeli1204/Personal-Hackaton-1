package com.example.hacka1.versionfinal.domain;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Entity
public class Limites {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String modelo;
    private String ventanaTiempo;
    private String limitesEspecificos;

    @ManyToOne
    Usuario usuario;

}
