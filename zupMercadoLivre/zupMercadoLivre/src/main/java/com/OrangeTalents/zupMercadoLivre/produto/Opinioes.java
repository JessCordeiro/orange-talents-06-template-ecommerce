package com.OrangeTalents.zupMercadoLivre.produto;

import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

import com.OrangeTalents.zupMercadoLivre.opiniao.Opiniao;

public class Opinioes {

	private Set<Opiniao> opinioes;

	public Opinioes(Set<Opiniao> opinioes) {
		this.opinioes = opinioes;
	}
	
	public <T> Set<T> mapeiaOpinioes(Function<Opiniao, T> funcaoMapeadora){
		return this.opinioes.stream().map(funcaoMapeadora).collect(Collectors.toSet());
	}
	
	
	
}
