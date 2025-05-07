package controller;

import domain.Restriccion;
import Service.RestriccionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/company/restrictions")
public class RestriccionController {

    @Autowired
    private RestriccionService restriccionService;

    // Crear nueva restricción
    @PostMapping
    public Restriccion crearRestriccion(@RequestParam UUID empresaId, @RequestBody Restriccion restriccion) {
        return restriccionService.crearRestriccion(empresaId, restriccion);
    }

    // Listar todas las restricciones de la empresa
    @GetMapping
    public List<Restriccion> listarRestricciones(@RequestParam UUID empresaId) {
        return restriccionService.listarRestricciones(empresaId);
    }

    // Actualizar una restricción existente
    @PutMapping("/{id}")
    public Restriccion actualizarRestriccion(@PathVariable UUID id, @RequestBody Restriccion restriccion) {
        return restriccionService.actualizarRestriccion(id, restriccion);
    }

    // Eliminar una restricción
    @DeleteMapping("/{id}")
    public void eliminarRestriccion(@PathVariable UUID id) {
        restriccionService.eliminarRestriccion(id);
    }
}

