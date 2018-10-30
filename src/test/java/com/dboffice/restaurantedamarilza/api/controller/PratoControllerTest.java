package com.dboffice.restaurantedamarilza.api.controller;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Optional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.BDDMockito;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.dboffice.restaurantedamarilza.api.entities.PratoEntity;
import com.dboffice.restaurantedamarilza.api.services.PratoService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
public class PratoControllerTest {

	@Autowired
	private MockMvc mvc;

	@MockBean
	private PratoService pratoService;

	private static final String URL_BASE = "/api/prato/";
	private static final Long ID = new Long(1);
	private static final String DESCRICAO = "Nada acotece feijoada";
	private static final String RECEITA = "Receita teste";
	private static final String OBSERVACAO= "Observação teste";
	
	@Test
	@WithMockUser
	public void testNewPrato() throws Exception {
		PratoEntity pratoEntity = newEntity();
		BDDMockito.given(this.pratoService.findByDescricao(Mockito.anyString())).willReturn(Optional.empty());
		BDDMockito.given(this.pratoService.findById(Mockito.anyLong())).willReturn(Optional.of(new PratoEntity()));
		BDDMockito.given(this.pratoService.save(Mockito.any(PratoEntity.class))).willReturn(pratoEntity);

		mvc.perform(MockMvcRequestBuilders.post(URL_BASE)
				.content(this.jsonToPost())
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.data.descricao").value(DESCRICAO))
				.andExpect(jsonPath("$.data.receita").value(RECEITA))
				.andExpect(jsonPath("$.data.observacao").value(OBSERVACAO))
				.andExpect(jsonPath("$.errors").isEmpty());
	}
	
	private PratoEntity newEntity() {
		PratoEntity pratoEntity = new PratoEntity();
		pratoEntity.setId(ID);
		pratoEntity.setDescricao(DESCRICAO);
		pratoEntity.setReceita(RECEITA);
		pratoEntity.setObservacao(OBSERVACAO);
		
		return pratoEntity;
	}
	
	private String jsonToPost() throws JsonProcessingException {
		PratoDTO pratoDTO = new PratoDTO();
		pratoDTO.setId(null);
		pratoDTO.setDescricao(DESCRICAO);
		pratoDTO.setReceita(RECEITA);
		pratoDTO.setObservacao(OBSERVACAO);
		
		ObjectMapper mapper = new ObjectMapper();
		return mapper.writeValueAsString(pratoDTO);
	}
	
	private class PratoDTO {
		private Long id;
		private String descricao;
		private String receita;
		private String observacao;
		public Long getId() {
			return id;
		}
		public void setId(Long id) {
			this.id = id;
		}
		public String getDescricao() {
			return descricao;
		}
		public void setDescricao(String descricao) {
			this.descricao = descricao;
		}
		public String getReceita() {
			return receita;
		}
		public void setReceita(String receita) {
			this.receita = receita;
		}
		public String getObservacao() {
			return observacao;
		}
		public void setObservacao(String observacao) {
			this.observacao = observacao;
		}
		
	}

}
