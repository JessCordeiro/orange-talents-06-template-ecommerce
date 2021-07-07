package com.OrangeTalents.zupMercadoLivre.security;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.OrangeTalents.zupMercadoLivre.usuario.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long>{

	Optional<Usuario> findByLogin(String login);

}
