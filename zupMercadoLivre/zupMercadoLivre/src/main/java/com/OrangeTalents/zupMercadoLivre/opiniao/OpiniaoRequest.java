package com.OrangeTalents.zupMercadoLivre.opiniao;

import javax.validation.Valid;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.OrangeTalents.zupMercadoLivre.produto.Produto;
import com.OrangeTalents.zupMercadoLivre.usuario.Usuario;

public class OpiniaoRequest {

	@NotNull
	@Min(1)
	@Max(5)
	private Double nota;
	
	@NotBlank
	private String titulo;
	
	@NotBlank
	@Size(max=500)
	private String descricao;

	public OpiniaoRequest(@NotNull @Min(1) @Max(5) Double nota, @NotBlank String titulo,
			@NotBlank @Size(max = 500) String descricao) {
		super();
		this.nota = nota;
		this.titulo = titulo;
		this.descricao = descricao;
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
	
	public Opiniao toModel(@NotNull @Valid Usuario usuario,Produto produto) {
		return new Opiniao(this.nota, this.titulo, this.descricao, usuario, produto);
	}
}
