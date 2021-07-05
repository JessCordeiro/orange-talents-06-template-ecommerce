package com.OrangeTalents.zupMercadoLivre.categoria;

import javax.persistence.EntityManager;
import javax.validation.constraints.NotBlank;


import com.OrangeTalents.zupMercadoLivre.validacao.ExistsId;
import com.OrangeTalents.zupMercadoLivre.validacao.UniqueValue;



public class CategoriaRequest {

	@NotBlank
	@UniqueValue(domainClass = Categoria.class, fieldName = "nome")
	private String nome;
	
	@ExistsId(domainClass = Categoria.class,fieldName = "id")
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
