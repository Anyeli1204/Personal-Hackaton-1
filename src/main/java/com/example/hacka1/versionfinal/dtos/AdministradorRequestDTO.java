package com.example.hacka1.versionfinal.dtos;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class AdministradorRequestDTO {
    @NotNull
    private String nombre;
    @NotNull
    private String apellido;
}
