package com.OrangeTalents.zupMercadoLivre.usuario;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

	@PersistenceContext
	private EntityManager em;

	
	@PostMapping
	@Transactional	
	public ResponseEntity<?> cadastrar(@RequestBody @Valid UsuarioRequest usuarioRequest) {
		Usuario usuario = usuarioRequest.toModel();
		em.persist(usuario);
		return ResponseEntity.ok(usuario);
		
				
	}
}
