package com.OrangeTalents.zupMercadoLivre.produto;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.ManyToOne;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;

import com.OrangeTalents.zupMercadoLivre.categoria.Categoria;
import com.OrangeTalents.zupMercadoLivre.usuario.Usuario;




public class ProdutoRequest {
	
	@NotBlank
	private String nome;
	
	@NotNull
	@Positive
	private BigDecimal valor;
	
	@NotNull
	@Positive
	private Integer quantidade;
	
	
	
	@NotBlank
	@Size(max=1000)
	private String descricao;
	
	@NotNull
	@ManyToOne
	private Long idCategoria;
	
	@ManyToOne
	@NotNull
	private Long idUsuario;
	
	@Valid
	@Size(min=3)
	private List<CaracteristicaRequest> caracteristicas = new ArrayList<>();
	
	
	@Deprecated
	public ProdutoRequest() {
		super();
		// TODO Auto-generated constructor stub
	}


	public ProdutoRequest(@NotBlank String nome, @NotNull @Positive BigDecimal valor,
			@NotNull @Positive Integer quantidade,
			@NotBlank @Size(max = 1000) String descricao, @NotBlank Long idCategoria, Long idUsuario,
			@Valid List<CaracteristicaRequest> caracteristicas) {
		super();
		this.nome = nome;
		this.valor = valor;
		this.quantidade = quantidade;
		this.descricao = descricao;
		this.idCategoria = idCategoria;
		this.idUsuario = idUsuario;
		this.caracteristicas.addAll(caracteristicas);
	}


	public String getNome() {
		return nome;
	}



	public BigDecimal getValor() {
		return valor;
	}



	public Integer getQuantidade() {
		return quantidade;
	}



	public String getDescricao() {
		return descricao;
	}




	public Long getIdCategoria() {
		return idCategoria;
	}




	public Long getIdUsuario() {
		return idUsuario;
	}




	public List<CaracteristicaRequest> getCaracteristicas() {
		return caracteristicas;
	}


	


	public Produto toModel(EntityManager em) {
		
		
		Categoria categoria = em.find(Categoria.class, idCategoria);
		Usuario usuario = em.find(Usuario.class, idUsuario);
		 return new Produto(this.nome, this.valor, this.quantidade, this.descricao, categoria, usuario,caracteristicas);
	 }
	
	
	public Set<String> caracteristicasIguais(){
		HashSet<String> nomesIguais = new HashSet<>();
		HashSet<String> resultados = new HashSet<>();
		for (CaracteristicaRequest caracteristica : caracteristicas) {
			String nome = caracteristica.getNome();
			if (!nomesIguais.add(nome)) {
				resultados.add(nome);
			}
		}
		return resultados;
	}
	
	
}
