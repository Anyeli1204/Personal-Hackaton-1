package controller;

import domain.Empresa;
import domain.Administrador;
import Service.EmpresaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/admin/companies")
public class EmpresaController {

    @Autowired
    private EmpresaService empresaService;

    // Crear nueva empresa
    @PostMapping
    public Empresa crearEmpresa(@RequestBody Empresa empresa, @RequestBody Administrador administrador) {
        return empresaService.crearEmpresa(empresa, administrador);
    }

    // Listar todas las empresas
    @GetMapping
    public List<Empresa> listarEmpresas() {
        return empresaService.listarEmpresas();
    }

    // Obtener información de una empresa
    @GetMapping("/{id}")
    public Empresa obtenerEmpresa(@PathVariable UUID id) {
        return empresaService.obtenerEmpresaPorId(id);
    }

    // Actualizar información de una empresa
    @PutMapping("/{id}")
    public Empresa actualizarEmpresa(@PathVariable UUID id, @RequestBody Empresa empresa) {
        return empresaService.actualizarEmpresa(id, empresa);
    }

    // Activar/Desactivar empresa
    @PatchMapping("/{id}/status")
    public Empresa cambiarEstadoEmpresa(@PathVariable UUID id, @RequestParam boolean estado) {
        return empresaService.cambiarEstadoEmpresa(id, estado);
    }

    // Obtener reporte de consumo de la empresa
    @GetMapping("/{id}/consumption")
    public String obtenerConsumoEmpresa(@PathVariable UUID id) {
        return empresaService.obtenerConsumoEmpresa(id);
    }
}
