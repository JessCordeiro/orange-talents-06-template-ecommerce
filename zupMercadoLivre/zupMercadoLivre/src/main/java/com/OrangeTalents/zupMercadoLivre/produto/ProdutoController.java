package com.OrangeTalents.zupMercadoLivre.produto;

import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.OrangeTalents.zupMercadoLivre.security.UsuarioRepository;
import com.OrangeTalents.zupMercadoLivre.usuario.Usuario;




@RestController
@RequestMapping("/produtos")
public class ProdutoController {

	@PersistenceContext
	private EntityManager em;
	
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@Autowired
	private Uploader uploader;
	
	
	@PostMapping
	@Transactional	
	public ResponseEntity<?> cadastrar(@RequestBody @Valid ProdutoRequest produtoRequest) {
		
		Usuario usuario = usuarioRepository.findByLogin("xpto@gmail.com").get();
		Produto produto = produtoRequest.toModel(em, usuario);
		em.persist(produto);
		return ResponseEntity.ok(produto);
				
	}
	
	@PostMapping("/{id}/imagens")
	@Transactional	
	public ResponseEntity<?> adicionaImagens(@PathVariable("id") Long id, @Valid ImagemRequest request) {
		
		Usuario usuario = usuarioRepository.findByLogin("xpto@gmail.com").get();
		Produto produto = em.find(Produto.class, id);
		
		if(!produto.pertenceAoUsuario(usuario)) {
			throw new ResponseStatusException(HttpStatus.FORBIDDEN);
		}
		
		Set<String> links = uploader.enviar(request.getImagens());
		
		produto.associarImagens(links);
		
		em.merge(produto);
		
		
		return ResponseEntity.ok(produto);
				
	}
	
}
