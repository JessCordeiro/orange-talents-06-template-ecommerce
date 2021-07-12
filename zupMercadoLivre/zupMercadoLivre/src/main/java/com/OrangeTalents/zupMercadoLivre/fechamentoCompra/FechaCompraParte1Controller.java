package com.OrangeTalents.zupMercadoLivre.fechamentoCompra;

import java.net.BindException;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.OrangeTalents.zupMercadoLivre.pergunta.Emails;
import com.OrangeTalents.zupMercadoLivre.produto.Produto;
import com.OrangeTalents.zupMercadoLivre.security.UsuarioRepository;
import com.OrangeTalents.zupMercadoLivre.usuario.Usuario;

@RestController
public class FechaCompraParte1Controller {

	@PersistenceContext
	private EntityManager manager;
	@Autowired
	
	private UsuarioRepository usuarioRepository;
	@Autowired

	private Emails emails;

	@PostMapping(value = "/api/compras")
	@Transactional
	
	public String cria(@RequestBody @Valid CompraRequest request,
			UriComponentsBuilder uriComponentsBuilder)  throws BindException {

		
		Produto produtoASerComprado = manager.find(Produto.class,
				request.getIdProduto());

		int quantidade = request.getQuantidade();
		boolean abateu = produtoASerComprado.abataEstoque(quantidade);

		
		if (abateu) {
			Usuario comprador = usuarioRepository
					.findByLogin("email@gmail.com").get();
			
			GatewayPagamento gateway = request.getGateway();

			
			Compra novaCompra = new Compra(produtoASerComprado, quantidade,
					comprador, gateway);
			manager.persist(novaCompra);
			emails.novaCompra(novaCompra);
			
			return novaCompra.urlRedirecionamento(uriComponentsBuilder);
									
		}
		
		BindException problemaComEstoque = new BindException();
		
		//((Object) problemaComEstoque).reject(null,
			//	"Não foi possível realizar a compra por conta do estoque");

		throw problemaComEstoque;
		
	}
		

	}
