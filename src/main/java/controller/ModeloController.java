package controller;

import domain.Modelo;
import Service.ModeloService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/ai/models")
public class ModeloController {

    @Autowired
    private ModeloService modeloService;

    // Listar modelos disponibles
    @GetMapping
    public List<Modelo> listarModelos() {
        return modeloService.listarModelosDisponibles();
    }

    // Realizar consulta a modelo de chat
    @PostMapping("/chat")
    public String realizarConsultaChat(@RequestParam UUID usuarioId, @RequestBody String consulta) {
        return modeloService.realizarConsultaChat(usuarioId, consulta).getRespuesta();
    }

    // Realizar completado de texto
    @PostMapping("/completion")
    public String realizarCompletadoTexto(@RequestParam UUID usuarioId, @RequestBody String texto) {
        return modeloService.realizarCompletadoTexto(usuarioId, texto).getRespuesta();
    }

    // Realizar consulta multimodal
    @PostMapping("/multimodal")
    public String realizarConsultaMultimodal(@RequestParam UUID usuarioId, @RequestParam String texto, @RequestParam String imagen) {
        return modeloService.realizarConsultaMultimodal(usuarioId, texto, imagen).getRespuesta();
    }
}
