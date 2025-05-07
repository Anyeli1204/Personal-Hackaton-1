package com.example.hacka1.versionfinal.controller;

import com.example.hacka1.versionfinal.domain.Empresa;
import com.example.hacka1.versionfinal.dtos.ConsumoEmpresaResponseDTO;
import com.example.hacka1.versionfinal.dtos.EmpresaRequestDTO;
import com.example.hacka1.versionfinal.service.GestionEmpresaService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/admin/companies")
public class GestionEmpresaController {

    @Autowired
    private GestionEmpresaService gestionEmpresaService;

    // Crear nueva empresa únicamente con su administrador usando DTO para válidar
    @PostMapping
    public ResponseEntity<Empresa> createEmpresaAdministrador(@RequestBody @Valid EmpresaRequestDTO empresaDTO) {
        return gestionEmpresaService.createEmpresaAdministrador(empresaDTO);
    }

    // Obtiene empresas por su ID únicamente con su administrador usando DTO de salida
    @GetMapping
    public ResponseEntity<?> getAllEmpresas() {
        return gestionEmpresaService.getAllEmpresas();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getEmpresaByID(@PathVariable Long id) {
        return gestionEmpresaService.getEmpresaByID(id);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> actualizarTodoEmpresaAdministrador(@PathVariable Long id, @RequestBody @Valid EmpresaRequestDTO actualizarEmpresaDTO) {
        return gestionEmpresaService.actualizarTodoEmpresaAdministrador(id, actualizarEmpresaDTO);
    }

    @PatchMapping("/{id}/status")
    public ResponseEntity<?> statusEmpresa(@PathVariable Long id, @RequestBody Boolean status) {
        return gestionEmpresaService.statusEmpresa(id, status);
    }

    @GetMapping("/companies/{id}/consumption")
    public ResponseEntity<?> getConsumoEmpresa(@PathVariable Long id) {
        try {
            ConsumoEmpresaResponseDTO dto = gestionEmpresaService.calcularConsumoEmpresa(id);
            return ResponseEntity.ok(dto);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(404).body(e.getMessage());
        }
    }
}
