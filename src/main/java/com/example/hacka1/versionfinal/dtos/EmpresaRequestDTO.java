package com.example.hacka1.versionfinal.dtos;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.validation.constraints.NotNull;
import lombok.Data;


@Data
public class EmpresaRequestDTO {
    @NotNull
    private String nombre;
    @NotNull
    private String fechaAfiliacion;
    @NotNull
    private Boolean isActivo;
    @NotNull
    private AdministradorRequestDTO administrador;
}
