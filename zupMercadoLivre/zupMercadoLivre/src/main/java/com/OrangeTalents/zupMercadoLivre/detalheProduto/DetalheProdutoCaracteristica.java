package com.OrangeTalents.zupMercadoLivre.detalheProduto;

import com.OrangeTalents.zupMercadoLivre.produto.CaracteristicaProduto;

public class DetalheProdutoCaracteristica {
	
	private String nome;
	private String descricao;
	
	
	@Deprecated
	public DetalheProdutoCaracteristica() {
		
	}


	public DetalheProdutoCaracteristica(CaracteristicaProduto caracteristicaProduto) {
		this.nome = caracteristicaProduto.getNome();
		this.descricao = caracteristicaProduto.getDescricao();
	}


	public String getNome() {
		return nome;
	}


	public String getDescricao() {
		return descricao;
	}
	
	
	
	
}
