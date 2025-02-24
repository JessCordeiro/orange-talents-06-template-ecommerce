package com.OrangeTalents.zupMercadoLivre.pergunta;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.OrangeTalents.zupMercadoLivre.fechamentoCompra.Compra;

@Service
public class Emails {
	
	@Autowired
	private Mailer mailer;
	
	public void enviaEmailNovaPergunta(@NotNull @Valid Pergunta pergunta) {
		mailer.send("Corpo do email","Nova pergunta" , pergunta.getUsuario().getLogin(),"xpto@gmail.com", pergunta.getUsuario().getLogin());
	}

	public void novaCompra(Compra novaCompra) {
		mailer.send("nova compra..." + novaCompra, "Você tem uma nova compra",
				novaCompra.getComprador().getLogin(),
				"compras@nossomercadolivre.com",
				novaCompra.getDonoProduto().getLogin());
		
	}
	
	
}
