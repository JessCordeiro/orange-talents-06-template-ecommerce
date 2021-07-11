package com.OrangeTalents.zupMercadoLivre.produto;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;
import java.util.function.Function;
import java.util.stream.Collectors;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;

import com.OrangeTalents.zupMercadoLivre.categoria.Categoria;
import com.OrangeTalents.zupMercadoLivre.opiniao.Opiniao;
import com.OrangeTalents.zupMercadoLivre.pergunta.Pergunta;
import com.OrangeTalents.zupMercadoLivre.usuario.Usuario;



@Entity
public class Produto {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
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
	private Categoria categoria;
	
	@ManyToOne
	@NotNull
	private Usuario usuario;
	
	private LocalDateTime dataCadastro = LocalDateTime.now();
	
	@OneToMany(mappedBy = "produto", cascade = CascadeType.PERSIST)
	@Valid
	@Size(min=3)
	private Set<CaracteristicaProduto> caracteristicas = new HashSet<>();
	
	@OneToMany(mappedBy = "produto", cascade = CascadeType.MERGE)
	private Set<ImagemProduto> imagens = new HashSet<>();
	
	@OneToMany(mappedBy = "produto", cascade = CascadeType.MERGE)
	private Set<Opiniao> opinioes = new HashSet<>();
	
	@OneToMany(mappedBy= "produto")@OrderBy("titulo asc")
	private SortedSet<Pergunta> perguntas = new TreeSet<>();	
	@Deprecated
	public Produto() {
		
	}

	public Produto(@NotBlank String nome, @NotNull @Positive BigDecimal valor, @NotNull @Positive Integer quantidade,
			@NotBlank @Size(max = 1000) String descricao, @NotNull Categoria categoria, @NotNull Usuario usuario,
			@Valid @Size(min = 3) Collection<CaracteristicaRequest> caracteristicas) {
		super();
		this.nome = nome;
		this.valor = valor;
		this.quantidade = quantidade;
		this.descricao = descricao;
		this.categoria = categoria;
		this.usuario = usuario;
		this.caracteristicas.addAll(caracteristicas.stream().map(caracteristica -> caracteristica.toModel(this))
				.collect(Collectors.toSet()));
		
		
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public BigDecimal getValor() {
		return valor;
	}

	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}

	public Integer getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(Integer quantidade) {
		this.quantidade = quantidade;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Categoria getCategoria() {
		return categoria;
	}

	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public Set<CaracteristicaProduto> getCaracteristicas() {
		return this.caracteristicas;
	}
	
	
	
	public Long getId() {
		return id;
	}

	public LocalDateTime getDataCadastro() {
		return dataCadastro;
	}

	public Set<ImagemProduto> getImagens() {
		return imagens;
	}
	

	public void setImagens(Set<ImagemProduto> imagens) {
		this.imagens = imagens;
	}
	
	

	public Set<Opiniao> getOpinioes() {
		return opinioes;
	}
	
	//aqui
	public Produto(Set<Opiniao> opinioes) {
		this.opinioes = opinioes;
	}
	
	

	public SortedSet<Pergunta> getPerguntas() {
		return perguntas;
	}

	public <T> Set<T> mapeiaCaracteristicas(Function<CaracteristicaProduto, T> funcaoMapeadora){
		return this.caracteristicas.stream()
				.map(funcaoMapeadora)
				.collect(Collectors.toSet());
	}

	
	public void associarImagens(Set<String> links) {
		Set<ImagemProduto> imagens = links.stream()
				.map(link -> new ImagemProduto(this, link))
				.collect(Collectors.toSet());
		
		this.imagens.addAll(imagens);
	}
	
	
	public <T> Set <T> mapeiaImagens(Function<ImagemProduto, T> funcaoMapeadora){
		return this.imagens.stream()
				.map(funcaoMapeadora)
				.collect(Collectors.toSet());
	}
	
	
	public boolean pertenceAoUsuario(Usuario usuarioCerto) {
		return this.usuario.equals(usuarioCerto);
	}
	
	public <T extends Comparable<T>> SortedSet<T> mapearPerguntas(Function<Pergunta, T> funcaoMapeadora){
		return this.perguntas.stream().map(funcaoMapeadora)
				.collect(Collectors.toCollection(TreeSet ::new));
	}
	


	
	
	

}
