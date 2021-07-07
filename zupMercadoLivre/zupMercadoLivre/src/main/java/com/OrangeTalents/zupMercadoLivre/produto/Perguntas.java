package com.OrangeTalents.zupMercadoLivre.produto;

import java.util.SortedSet;
import java.util.TreeSet;
import java.util.function.Function;
import java.util.stream.Collectors;

import com.OrangeTalents.zupMercadoLivre.pergunta.Pergunta;



public class Perguntas {

	private SortedSet<Pergunta> perguntas;

	public void setPerguntas(SortedSet<Pergunta> perguntas) {
		this.perguntas = perguntas;
	}

	public <T extends Comparable<T>> SortedSet<T> mapearPerguntas(Function<Pergunta, T> funcaoMapeadora){
		return this.perguntas.stream().map(funcaoMapeadora)
				.collect(Collectors.toCollection(TreeSet ::new));
	}
	
}
