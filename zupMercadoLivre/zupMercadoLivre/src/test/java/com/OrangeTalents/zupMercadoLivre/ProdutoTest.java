package com.OrangeTalents.zupMercadoLivre;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.ignoreStubs;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.AutoConfigureDataJpa;
import org.springframework.boot.test.autoconfigure.orm.jpa.AutoConfigureTestEntityManager;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.transaction.annotation.Transactional;

import com.OrangeTalents.zupMercadoLivre.categoria.Categoria;
import com.OrangeTalents.zupMercadoLivre.produto.CaracteristicaProduto;
import com.OrangeTalents.zupMercadoLivre.produto.CaracteristicaRequest;
import com.OrangeTalents.zupMercadoLivre.produto.Produto;
import com.OrangeTalents.zupMercadoLivre.produto.ProdutoRequest;
import com.OrangeTalents.zupMercadoLivre.usuario.Usuario;
import com.fasterxml.jackson.databind.ObjectMapper;
//import org.springframework.security.test.context.support.WithUserDetails;


@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureDataJpa
@AutoConfigureTestEntityManager
@Transactional
public class ProdutoTest {
	
		@Autowired
		private MockMvc mockMvc;
		
		@Autowired
		private EntityManager manager;
		
		@Autowired
		ObjectMapper objectMapper;
		
		private Categoria categoria;
		
		private BigDecimal quarenta = new BigDecimal(40);
		
		
		@BeforeEach
		public void persistir() {
			Usuario usuario = new Usuario("email@gmail.com", "1234567");
			manager.persist(usuario);
			
			categoria = new Categoria("categoria");
			manager.persist(categoria);
			
			
		}
		
		

	
		private List<CaracteristicaRequest> caracteristicasRequest(int n){
			List<CaracteristicaRequest> caracteristicas = new ArrayList<CaracteristicaRequest>();
			for(int i=0; i<n; i++)
				caracteristicas.add(new CaracteristicaRequest("nome" +i, "valor"));
			return caracteristicas;
		}
		
		public static MvcResult performPost(MockMvc mockMvc, String url, int status, ObjectMapper objectMapper, Object request) throws Exception{
			return mockMvc.perform(MockMvcRequestBuilders
					.post(url)
					.contentType(MediaType.APPLICATION_JSON)
					.content(objectMapper.writeValueAsString(request)))
					.andExpect(MockMvcResultMatchers.status().is(status)).andReturn();
		}
		
		
		@Test
		//@WithUserDetails("teste@logado.com")
		public void naoCadastrarProdutoSemUsuario() throws Exception{
			List<CaracteristicaRequest> caracteristicas = caracteristicasRequest(3);
			
			
			ProdutoRequest request = new ProdutoRequest("nome",quarenta, 2,
					"Descrição do produto",this.categoria.getId(), caracteristicas);
			
			performPost(mockMvc, "/produtos", 403, objectMapper, request);
			
			List<Produto> produtos = manager.createQuery("select * from Produto",  Produto.class).getResultList();
			
			assertTrue(produtos.size()==0);
		}
		
		@Test 
		//@WithUserDetails("teste@logado.com")
		public void naoCadastrarProdutoSemCategoria() throws Exception{
			List<CaracteristicaRequest> caracteristicas = caracteristicasRequest(3);
			
			ProdutoRequest request = new ProdutoRequest("nome",quarenta, 2,
					"Descrição do produto",this.categoria.getId() + 1, caracteristicas);
			
			performPost(mockMvc, "/produtos", 400, objectMapper, request);
			
			List<Produto> produtos = manager.createQuery("select * from Produto",  Produto.class).getResultList();
			
			assertTrue(produtos.size()==0);
		}

		
	

		
}
