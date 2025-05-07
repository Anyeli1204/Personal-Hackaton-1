package com.example.hacka1.versionfinal.dtos;

import lombok.Data;

import java.util.Map;

@Data
public class ConsumoEmpresaResponseDTO {
    private Long idEmpresa;
    private Integer totalTokens;
    private Map<String, Integer> consumoPorModelo;

}