package com.OrangeTalents.zupMercadoLivre.produto;

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
@RequestMapping("/produtos")
public class ProdutoController {

	@PersistenceContext
	private EntityManager em;
	
	
	@PostMapping
	@Transactional	
	public ResponseEntity<?> cadastrar(@RequestBody @Valid ProdutoRequest produtoRequest) {
		
		
		Produto produto = produtoRequest.toModel(em);
		em.persist(produto);
		return ResponseEntity.ok(produto);
	
				
	}
}
