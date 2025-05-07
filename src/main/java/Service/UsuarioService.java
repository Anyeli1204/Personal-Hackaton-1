package Service;

import domain.Usuario;
import domain.Empresa;
import domain.Limite;
import repository.UsuarioRepository;
import repository.EmpresaRepository;
import repository.LimiteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private EmpresaRepository empresaRepository;

    @Autowired
    private LimiteRepository limiteRepository;

    // Crear nuevo usuario
    public Usuario crearUsuario(UUID empresaId, Usuario usuario) {
        Empresa empresa = empresaRepository.findById(empresaId)
                .orElseThrow(() -> new RuntimeException("Empresa no encontrada"));
        usuario.setEmpresa(empresa);
        return usuarioRepository.save(usuario);
    }

    // Listar todos los usuarios de una empresa
    public List<Usuario> listarUsuarios(UUID empresaId) {
        Empresa empresa = empresaRepository.findById(empresaId)
                .orElseThrow(() -> new RuntimeException("Empresa no encontrada"));
        return empresa.getUsuarios();
    }

    // Obtener usuario por ID
    public Usuario obtenerUsuarioPorId(UUID id) {
        return usuarioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
    }

    // Actualizar información de usuario
    @Transactional
    public Usuario actualizarUsuario(UUID id, Usuario usuarioActualizado) {
        Usuario usuario = obtenerUsuarioPorId(id);
        usuario.setNombreCompleto(usuarioActualizado.getNombreCompleto());
        usuario.setEmail(usuarioActualizado.getEmail());
        return usuarioRepository.save(usuario);
    }

    // Asignar límite específico a un usuario
    public Limite asignarLimite(UUID usuarioId, Limite limite) {
        Usuario usuario = obtenerUsuarioPorId(usuarioId);
        limite.setUsuario(usuario);
        return limiteRepository.save(limite);
    }

    // Obtener reporte de consumo de usuario (simplificado)
    public String obtenerConsumoUsuario(UUID usuarioId) {
        Usuario usuario = obtenerUsuarioPorId(usuarioId);
        return "Consumo total: 500 tokens"; // Lógica real por implementar
    }
}
