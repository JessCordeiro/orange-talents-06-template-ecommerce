package com.OrangeTalents.zupMercadoLivre.produto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class CaracteristicaRequest {
	
	
	@NotBlank
	private String nome;
	
	@NotBlank
	private String descricao;

	public CaracteristicaRequest(@NotBlank String nome, @NotBlank String descricao) {
		super();
		this.nome = nome;
		this.descricao = descricao;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((descricao == null) ? 0 : descricao.hashCode());
		result = prime * result + ((nome == null) ? 0 : nome.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		CaracteristicaRequest other = (CaracteristicaRequest) obj;
		if (descricao == null) {
			if (other.descricao != null)
				return false;
		} else if (!descricao.equals(other.descricao))
			return false;
		if (nome == null) {
			if (other.nome != null)
				return false;
		} else if (!nome.equals(other.nome))
			return false;
		return true;
	}
	
	public CaracteristicaProduto toModel(@NotNull Produto produto) {
		return new CaracteristicaProduto(nome, descricao, produto);
	}
	
	
	

}
