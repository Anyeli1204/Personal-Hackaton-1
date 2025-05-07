package Service;

import domain.Limite;
import domain.Usuario;
import repository.LimiteRepository;
import repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
public class LimiteService {

    @Autowired
    private LimiteRepository limiteRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    // Crear un nuevo límite para un usuario
    public Limite crearLimite(UUID usuarioId, Limite limite) {
        Usuario usuario = usuarioRepository.findById(usuarioId)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
        limite.setUsuario(usuario);
        return limiteRepository.save(limite);
    }

    // Listar todos los límites de un usuario
    public List<Limite> listarLimitesUsuario(UUID usuarioId) {
        Usuario usuario = usuarioRepository.findById(usuarioId)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
        return usuario.getLimites();
    }

    // Actualizar un límite
    @Transactional
    public Limite actualizarLimite(UUID id, Limite limiteActualizado) {
        Limite limite = limiteRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Límite no encontrado"));
        limite.setModeloIA(limiteActualizado.getModeloIA());
        limite.setLimiteUso(limiteActualizado.getLimiteUso());
        limite.setLimiteTokens(limiteActualizado.getLimiteTokens());
        limite.setVentanaTiempo(limiteActualizado.getVentanaTiempo());
        return limiteRepository.save(limite);
    }

    // Eliminar un límite
    @Transactional
    public void eliminarLimite(UUID id) {
        if (!limiteRepository.existsById(id)) {
            throw new RuntimeException("Límite no encontrado");
        }
        limiteRepository.deleteById(id);
    }
    public boolean verificarLimite(String usuarioId) {
        // Lógica para verificar el límite del usuario
        return true; // Cambia esto por la lógica adecuada
    }
}
