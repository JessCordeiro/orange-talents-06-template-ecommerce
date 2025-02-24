package com.OrangeTalents.zupMercadoLivre.produto;

import java.util.ArrayList;
import java.util.List;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.web.multipart.MultipartFile;

public class ImagemRequest {

	@Size(min=1)
	@NotNull
	private List<MultipartFile> imagens = new ArrayList<>();

	public List<MultipartFile> getImagens() {
		return imagens;
	}

	public void setImagens(List<MultipartFile> imagens) {
		this.imagens = imagens;
	}
	
	

}
