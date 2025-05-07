package com.example.hacka1.versionfinal.domain;

import jakarta.persistence.*;
import lombok.Data;


@Entity
@Data
public class Solicitud {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String consulta;
    private String respuesta;
    private Integer tokensConsumidos;
    private String modelo;

    @ManyToOne
    private Usuario usuario;
}
