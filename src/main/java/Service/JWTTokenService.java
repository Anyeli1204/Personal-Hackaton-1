package Service;

import domain.JWTToken;
import domain.Usuario;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import repository.JWTTokenRepository;
import repository.UsuarioRepository;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Collections;
import java.util.Date;
import java.util.UUID;

@Service
public class JWTTokenService implements UserDetailsService {

    @Autowired
    private JWTTokenRepository jwtTokenRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    private final String SECRET_KEY = "your-secret-key"; // Debe estar segura en un entorno seguro

    // Implementación de UserDetailsService
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Usuario usuario = usuarioRepository.findByEmail(username)
                .orElseThrow(() -> new UsernameNotFoundException("Usuario no encontrado: " + username));

        // Configurar roles según el rol del usuario
        return new User(
                usuario.getEmail(),
                usuario.getPassword(),
                Collections.singleton(usuario.getRol().equals("ROLE_SPARKY_ADMIN") ? new SimpleGrantedAuthority("ROLE_SPARKY_ADMIN") :
                        usuario.getRol().equals("ROLE_COMPANY_ADMIN") ? new SimpleGrantedAuthority("ROLE_COMPANY_ADMIN") :
                                new SimpleGrantedAuthority("ROLE_USER"))
        );
    }

    // Generar un nuevo token JWT
    public String generarToken(UUID usuarioId) {
        Usuario usuario = usuarioRepository.findById(usuarioId)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        String token = Jwts.builder()
                .setSubject(usuario.getEmail())
                .setIssuedAt(new Date())
                .setExpiration(Date.from(LocalDateTime.now().plusHours(2).toInstant(ZoneOffset.UTC)))
                .signWith(SignatureAlgorithm.HS256, SECRET_KEY)
                .compact();

        JWTToken jwtToken = new JWTToken();
        jwtToken.setToken(token);
        jwtToken.setFechaCreacion(LocalDateTime.now());
        jwtToken.setFechaExpiracion(LocalDateTime.now().plusHours(2));
        jwtToken.setUsuario(usuario);

        jwtTokenRepository.save(jwtToken);

        return token;
    }

    // Validar un token JWT
    public boolean validarToken(String token) {
        try {
            Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    // Revocar (eliminar) un token
    public void revocarToken(String token) {
        JWTToken jwtToken = jwtTokenRepository.findAll().stream()
                .filter(t -> t.getToken().equals(token))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Token no encontrado"));

        jwtTokenRepository.delete(jwtToken);
    }
}
