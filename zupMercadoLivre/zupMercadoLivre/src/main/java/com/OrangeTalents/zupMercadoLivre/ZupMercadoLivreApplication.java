package com.OrangeTalents.zupMercadoLivre;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication(exclude = {SecurityAutoConfiguration.class}) 
public class ZupMercadoLivreApplication {

	public static void main(String[] args) {
		SpringApplication.run(ZupMercadoLivreApplication.class, args);
	}

}
