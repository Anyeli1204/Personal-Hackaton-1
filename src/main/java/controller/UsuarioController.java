package controller;

import domain.Usuario;
import domain.Limite;
import Service.UsuarioService;
import Service.LimiteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/company/users")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private LimiteService limiteService;

    // Crear nuevo usuario
    @PostMapping
    public Usuario crearUsuario(@RequestParam UUID empresaId, @RequestBody Usuario usuario) {
        return usuarioService.crearUsuario(empresaId, usuario);
    }

    // Listar todos los usuarios
    @GetMapping
    public List<Usuario> listarUsuarios(@RequestParam UUID empresaId) {
        return usuarioService.listarUsuarios(empresaId);
    }

    // Obtener información de un usuario
    @GetMapping("/{id}")
    public Usuario obtenerUsuario(@PathVariable UUID id) {
        return usuarioService.obtenerUsuarioPorId(id);
    }

    // Actualizar información de usuario
    @PutMapping("/{id}")
    public Usuario actualizarUsuario(@PathVariable UUID id, @RequestBody Usuario usuario) {
        return usuarioService.actualizarUsuario(id, usuario);
    }

    // Asignar límite a un usuario
    @PostMapping("/{id}/limits")
    public Limite asignarLimite(@PathVariable UUID id, @RequestBody Limite limite) {
        return usuarioService.asignarLimite(id, limite);
    }

    // Obtener reporte de consumo del usuario
    @GetMapping("/{id}/consumption")
    public String obtenerConsumoUsuario(@PathVariable UUID id) {
        return usuarioService.obtenerConsumoUsuario(id);
    }
}
