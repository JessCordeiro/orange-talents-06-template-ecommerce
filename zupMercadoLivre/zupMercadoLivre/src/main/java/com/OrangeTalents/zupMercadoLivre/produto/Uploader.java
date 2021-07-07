package com.OrangeTalents.zupMercadoLivre.produto;

import java.util.List;
import java.util.Set;

import org.springframework.web.multipart.MultipartFile;

public interface Uploader {
	
	Set<String> enviar(List<MultipartFile> imagens);
}
