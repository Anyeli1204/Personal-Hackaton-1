package com.example.hacka1.versionfinal.dtos;

import lombok.Data;
@Data
public class EmpresasReponseDTO {
    private Long id;
    private String nombre;
    private String fechaAfiliacion;
    private Boolean isActivo;
    private String nombreAdministrador;
    private String apellidoAdministrador;
}
