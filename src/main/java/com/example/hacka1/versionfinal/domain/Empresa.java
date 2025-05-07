package com.example.hacka1.versionfinal.domain;

import com.example.hacka1.versionfinal.domain.Administrador;
import com.example.hacka1.versionfinal.domain.Restriccion;
import com.example.hacka1.versionfinal.domain.Usuario;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Data;
import java.util.List;

@Entity
@Data
public class Empresa {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nombre;
    private String fechaAfiliacion;
    private Boolean isActivo;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "administrador_id")
    @JsonManagedReference
    private Administrador administrador;

    @OneToMany(mappedBy = "empresa")
    private List<Usuario> usuarios;

    @OneToMany(mappedBy = "empresa")
    private List<Restriccion> restricciones;

    @ManyToOne
    private Sparky sparky;
}
