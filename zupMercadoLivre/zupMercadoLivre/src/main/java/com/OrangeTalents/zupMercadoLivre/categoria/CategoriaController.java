package com.OrangeTalents.zupMercadoLivre.categoria;

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
@RequestMapping("/categorias")
public class CategoriaController {
	

	@PersistenceContext
	private EntityManager em;
	
	@PostMapping
	@Transactional	
	public ResponseEntity<Categoria> cadastrar(@RequestBody @Valid CategoriaRequest categoriaRequest) {
		Categoria categoria = categoriaRequest.toModel(em);
		em.persist(categoria);
		return ResponseEntity.ok(categoria);
		
				
	}
}
