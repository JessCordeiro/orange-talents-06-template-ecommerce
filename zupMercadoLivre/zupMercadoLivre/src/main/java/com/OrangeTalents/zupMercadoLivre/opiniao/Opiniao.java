package com.OrangeTalents.zupMercadoLivre.opiniao;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.OrangeTalents.zupMercadoLivre.produto.Produto;
import com.OrangeTalents.zupMercadoLivre.usuario.Usuario;



@Entity
public class Opiniao {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotNull
	@Min(1)
	@Max(5)
	private Double nota;
	
	@NotBlank
	private String titulo;
	
	@NotBlank
	@Size(max=500)
	private String descricao;
	
	@ManyToOne
	@NotNull
	private Usuario usuario;
	
	@ManyToOne
	@NotNull
	private Produto produto;

	
	
	@Deprecated
	public Opiniao() {
		
	}
	
	

	public Opiniao(@NotNull @Min(1) @Max(5) Double nota, @NotBlank String titulo,
			@NotBlank @Size(max = 500) String descricao, @NotNull Usuario usuario, @NotNull Produto produto) {
		super();
		this.nota = nota;
		this.titulo = titulo;
		this.descricao = descricao;
		this.usuario = usuario;
		this.produto = produto;
	}



	public Long getId() {
		return id;
	}

	public Double getNota() {
		return nota;
	}

	public String getTitulo() {
		return titulo;
	}

	public String getDescricao() {
		return descricao;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public Produto getProduto() {
		return produto;
	}
	
	
	
}
