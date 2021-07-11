package com.OrangeTalents.zupMercadoLivre.detalheProduto;

import java.math.BigDecimal;
import java.util.Map;
import java.util.Set;
import java.util.SortedSet;

import com.OrangeTalents.zupMercadoLivre.opiniao.Opiniao;
import com.OrangeTalents.zupMercadoLivre.pergunta.Pergunta;
import com.OrangeTalents.zupMercadoLivre.produto.Opinioes;
import com.OrangeTalents.zupMercadoLivre.produto.Perguntas;
import com.OrangeTalents.zupMercadoLivre.produto.Produto;

public class DetalheProduto {
	
	private String nome;
	private BigDecimal valor;
	private String descricao;
	private Set<DetalheProdutoCaracteristica> caracteristicas;
	private Set<String> linksImagens;
	private SortedSet<String> perguntas;
	private Set<Map<String,String>> opinioes;
	private double mediaNotas;
	private int total;
	
	@Deprecated
	public DetalheProduto() {
	
	}
	
	public DetalheProduto(Produto produto) {
		
		this.nome = produto.getNome();
		this.valor = produto.getValor();
		this.descricao = produto.getDescricao();
		this.caracteristicas= produto.mapeiaCaracteristicas(DetalheProdutoCaracteristica :: new);
		this.linksImagens= produto.mapeiaImagens(imagem -> imagem.getLink());
		
		Opinioes opinioes =  (Opinioes) produto.getOpinioes();
		this.opinioes = opinioes.mapeiaOpinioes(opiniao -> {
			return Map.of("titulo",opiniao.getTitulo(),"descricao",opiniao.getDescricao());
		});

		
		this.perguntas = produto.mapearPerguntas(pergunta -> pergunta.getTitulo());
		
		this.mediaNotas = opinioes.media();
		this.total = opinioes.total();

		
		
	}

	public String getNome() {
		return nome;
	}

	public BigDecimal getValor() {
		return valor;
	}

	public String getDescricao() {
		return descricao;
	}

	public Set<DetalheProdutoCaracteristica> getCaracteristicas() {
		return caracteristicas;
	}

	public Set<String> getLinksImagens() {
		return linksImagens;
	}

	public SortedSet<String> getPerguntas() {
		return perguntas;
	}

	public Set<Map<String, String>> getOpinioes() {
		return opinioes;
	}

	public double getMediaNotas() {
		return mediaNotas;
	}

	public int getTotal() {
		return total;
	}
	
	
	
	
}
