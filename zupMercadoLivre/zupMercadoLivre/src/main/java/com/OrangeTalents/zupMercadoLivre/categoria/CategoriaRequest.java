package com.OrangeTalents.zupMercadoLivre.categoria;

import javax.persistence.EntityManager;
import javax.validation.constraints.NotBlank;



public class CategoriaRequest {

	@NotBlank
	private String nome;
	
	private Long idCategoriaMae;
	
	
	public CategoriaRequest() {
		
	}


	public CategoriaRequest(@NotBlank String nome, Long idCategoriaMae) {
		super();
		this.nome = nome;
		this.idCategoriaMae = idCategoriaMae;
	}


	public String getNome() {
		return nome;
	}



	public Long getIdCategoriaMae() {
		return idCategoriaMae;
	}


	public void setIdCategoriaMae(Long idCategoriaMae) {
		this.idCategoriaMae = idCategoriaMae;
	}
	
	
	public void setNome(String nome) {
		this.nome = nome;
	}


	public  Categoria toModel(EntityManager em) {
		Categoria categoria = new Categoria(nome);
		if(idCategoriaMae != null) {
			
			Categoria categoriaMae = em.find(Categoria.class, idCategoriaMae);
			
			categoria.setCategoriaMae(categoriaMae);
		}
		
		return categoria;
	}
	
	
}
