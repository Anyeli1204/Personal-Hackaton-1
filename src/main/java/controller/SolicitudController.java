package controller;

import domain.Solicitud;
import Service.SolicitudService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/ai")
public class SolicitudController {

    @Autowired
    private SolicitudService solicitudService;

    // Realizar consulta a modelo de IA (Chat)
    @PostMapping("/chat")
    public Solicitud realizarConsultaChat(@RequestParam UUID usuarioId, @RequestBody String consulta) {
        return solicitudService.crearSolicitud(usuarioId, null, new Solicitud(consulta, "Modelo de Chat", 50));
    }

    // Realizar completado de texto
    @PostMapping("/completion")
    public Solicitud realizarCompletadoTexto(@RequestParam UUID usuarioId, @RequestBody String texto) {
        return solicitudService.crearSolicitud(usuarioId, null, new Solicitud(texto, "Modelo de Completado", 100));
    }

    // Realizar consulta multimodal
    @PostMapping("/multimodal")
    public Solicitud realizarConsultaMultimodal(@RequestParam UUID usuarioId, @RequestParam String texto, @RequestParam String imagen) {
        return solicitudService.crearSolicitud(usuarioId, null, new Solicitud(texto + " [Imagen: " + imagen + "]", "Modelo Multimodal", 200));
    }

    // Obtener historial de solicitudes de un usuario
    @GetMapping("/history")
    public List<Solicitud> obtenerHistorialSolicitudes(@RequestParam UUID usuarioId) {
        return solicitudService.obtenerHistorialSolicitudes(usuarioId);
    }
}
