package Service;

import domain.Solicitud;
import domain.Usuario;
import domain.Empresa;
import repository.SolicitudRepository;
import repository.UsuarioRepository;
import repository.EmpresaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
public class SolicitudService {

    @Autowired
    private SolicitudRepository solicitudRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private EmpresaRepository empresaRepository;

    // Crear una nueva solicitud
    public Solicitud crearSolicitud(UUID usuarioId, UUID empresaId, Solicitud solicitud) {
        Usuario usuario = usuarioRepository.findById(usuarioId)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
        Empresa empresa = empresaRepository.findById(empresaId)
                .orElseThrow(() -> new RuntimeException("Empresa no encontrada"));

        solicitud.setUsuario(usuario);
        solicitud.setEmpresa(empresa);
        solicitud.setFechaHora(LocalDateTime.now());

        return solicitudRepository.save(solicitud);
    }

    // Listar solicitudes de un usuario
    public List<Solicitud> listarSolicitudesUsuario(UUID usuarioId) {
        Usuario usuario = usuarioRepository.findById(usuarioId)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
        return usuario.getHistorialSolicitudes();
    }

    // Obtener historial de solicitudes de un usuario
    public List<Solicitud> obtenerHistorialSolicitudes(UUID usuarioId) {
        Usuario usuario = usuarioRepository.findById(usuarioId)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
        return usuario.getHistorialSolicitudes();
    }

    // Obtener detalles de una solicitud
    public Solicitud obtenerSolicitudPorId(UUID id) {
        return solicitudRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Solicitud no encontrada"));
    }

    // Eliminar una solicitud
    @Transactional
    public void eliminarSolicitud(UUID id) {
        if (!solicitudRepository.existsById(id)) {
            throw new RuntimeException("Solicitud no encontrada");
        }
        solicitudRepository.deleteById(id);
    }
}
