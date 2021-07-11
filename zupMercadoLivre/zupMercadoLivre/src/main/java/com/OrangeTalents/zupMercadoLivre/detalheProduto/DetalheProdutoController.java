package com.OrangeTalents.zupMercadoLivre.detalheProduto;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.OrangeTalents.zupMercadoLivre.produto.Produto;
import com.OrangeTalents.zupMercadoLivre.usuario.Usuario;
import com.OrangeTalents.zupMercadoLivre.usuario.UsuarioRequest;

@RestController
public class DetalheProdutoController {
	
	@PersistenceContext
	private EntityManager em;

	
	@GetMapping("/produtos/{id}")
	@Transactional	
	public DetalheProduto cadastrar(@PathVariable("id") Long id) {
		
		
		Produto produtoEscolhido = em.find(Produto.class, id);
		
		
		return new DetalheProduto(produtoEscolhido);
		
				
	}
}
