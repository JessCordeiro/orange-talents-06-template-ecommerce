package com.OrangeTalents.zupMercadoLivre.pergunta;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.OrangeTalents.zupMercadoLivre.opiniao.Opiniao;
import com.OrangeTalents.zupMercadoLivre.opiniao.OpiniaoRequest;
import com.OrangeTalents.zupMercadoLivre.produto.Produto;
import com.OrangeTalents.zupMercadoLivre.security.UsuarioRepository;
import com.OrangeTalents.zupMercadoLivre.usuario.Usuario;

@RestController
@RequestMapping
public class PerguntaController {
	
	@PersistenceContext
	private EntityManager em;
	
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	
	@PostMapping(value ="/produtos/{id}/pergunta")
	@Transactional	
	public ResponseEntity<?> cadastrar(@RequestBody @Valid PerguntaRequest perguntaRequest, @PathVariable("id") Long id) {
		
		Usuario usuario = usuarioRepository.findByLogin("xpto@gmail.com").get();
		Produto produto = em.find(Produto.class, id);
		
		
		Pergunta pergunta = perguntaRequest.toModel(usuario, produto);
		em.persist(pergunta);
		return ResponseEntity.ok(pergunta);
		
	}
}
