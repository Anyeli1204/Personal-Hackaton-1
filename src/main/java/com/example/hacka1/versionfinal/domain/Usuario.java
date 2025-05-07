package com.example.hacka1.versionfinal.domain;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Entity
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany(mappedBy = "usuario")
    List<Limites> limites;

    @ManyToOne
    Empresa empresa;

    @OneToMany(mappedBy = "usuario")
    private List<Solicitud> solicitud;
}
