package com.dboffice.restaurantedamarilza.api.services;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.BDDMockito;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import com.dboffice.restaurantedamarilza.api.entities.PratoEntity;
import com.dboffice.restaurantedamarilza.api.repositories.PratoRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("test")
public class PratoServiceTest {

	@MockBean
	private PratoRepository pratoRepository;

	@Autowired
	private PratoService pratoService;

	private static final String DESCRICAO = "Feijoada";

	@Before
	public void setUp() throws Exception {
		BDDMockito.given(this.pratoRepository.findByDescricao(Mockito.anyString())).willReturn(new PratoEntity());
		BDDMockito.given(this.pratoRepository.save(Mockito.any(PratoEntity.class))).willReturn(new PratoEntity());
	}

	@Test
	public void testBuscarEmpresaPorCnpj() {
		Optional<PratoEntity> pratoEntity = this.pratoService.buscarPorDescricao(DESCRICAO);

		assertTrue(pratoEntity.isPresent());
	}

	@Test
	public void testPersistirEmpresa() {
		PratoEntity pratoEntity = this.pratoService.salvar(new PratoEntity());

		assertNotNull(pratoEntity);
	}

}
