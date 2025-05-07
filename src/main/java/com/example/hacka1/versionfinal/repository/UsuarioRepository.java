package com.example.hacka1.versionfinal.repository;

import com.example.hacka1.versionfinal.domain.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
}
