package controller;

import domain.JWTToken;
import Service.JWTTokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/auth")
public class JWTTokenController {

    @Autowired
    private JWTTokenService jwtTokenService;

    // Generar un nuevo token JWT para un usuario
    @PostMapping("/generate")
    public String generarToken(@RequestParam UUID usuarioId) {
        return jwtTokenService.generarToken(usuarioId);
    }

    // Verificar la validez de un token
    @GetMapping("/validate")
    public boolean validarToken(@RequestParam String token) {
        return jwtTokenService.validarToken(token);
    }

    // Revocar un token
    @DeleteMapping("/revoke")
    public void revocarToken(@RequestParam String token) {
        jwtTokenService.revocarToken(token);
    }
}
