package Service;

import domain.Modelo;
import domain.Solicitud;
import domain.Usuario;
import repository.ModeloRepository;
import repository.SolicitudRepository;
import repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class ModeloService {

    @Autowired
    private ModeloRepository modeloRepository;

    @Autowired
    private SolicitudRepository solicitudRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    // Listar modelos disponibles
    public List<Modelo> listarModelosDisponibles() {
        return modeloRepository.findAll();
    }

    // Realizar consulta a modelo de chat
    public Solicitud realizarConsultaChat(UUID usuarioId, String consulta) {
        Usuario usuario = usuarioRepository.findById(usuarioId)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        Solicitud solicitud = new Solicitud();
        solicitud.setTextoConsulta(consulta);
        solicitud.setModeloUtilizado("Modelo de Chat");
        solicitud.setTokensConsumidos(50); // Simulado
        solicitud.setUsuario(usuario);

        return solicitudRepository.save(solicitud);
    }

    // Realizar completado de texto
    public Solicitud realizarCompletadoTexto(UUID usuarioId, String texto) {
        Usuario usuario = usuarioRepository.findById(usuarioId)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        Solicitud solicitud = new Solicitud();
        solicitud.setTextoConsulta(texto);
        solicitud.setModeloUtilizado("Modelo de Completado");
        solicitud.setTokensConsumidos(100); // Simulado
        solicitud.setUsuario(usuario);

        return solicitudRepository.save(solicitud);
    }

    // Realizar consulta multimodal (texto + imagen)
    public Solicitud realizarConsultaMultimodal(UUID usuarioId, String texto, String imagen) {
        Usuario usuario = usuarioRepository.findById(usuarioId)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        Solicitud solicitud = new Solicitud();
        solicitud.setTextoConsulta(texto + " [Imagen: " + imagen + "]");
        solicitud.setModeloUtilizado("Modelo Multimodal");
        solicitud.setTokensConsumidos(200); // Simulado
        solicitud.setUsuario(usuario);

        return solicitudRepository.save(solicitud);
    }
}
