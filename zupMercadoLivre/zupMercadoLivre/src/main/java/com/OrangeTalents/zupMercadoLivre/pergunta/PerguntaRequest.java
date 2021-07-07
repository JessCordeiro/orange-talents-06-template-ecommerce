package com.OrangeTalents.zupMercadoLivre.pergunta;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.OrangeTalents.zupMercadoLivre.produto.Produto;
import com.OrangeTalents.zupMercadoLivre.usuario.Usuario;
import com.fasterxml.jackson.annotation.JsonCreator;

public class PerguntaRequest {
	
	@NotBlank
	private String titulo;
	
	@JsonCreator(mode = JsonCreator.Mode.PROPERTIES)
	public PerguntaRequest(@NotBlank String titulo) {
		super();
		this.titulo = titulo;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}
	
	public Pergunta toModel(@NotNull @Valid Usuario usuario, @NotNull @Valid Produto produto) {
		return new Pergunta(this.titulo, usuario, produto);
	}
	
	
}
