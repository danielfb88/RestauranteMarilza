package com.dboffice.restaurantedamarilza.api.repositories;

import static org.junit.Assert.assertEquals;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import com.dboffice.restaurantedamarilza.api.entities.PratoEntity;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("test")
public class PratoRepositoryTest {

	@Autowired
	private PratoRepository pratoRepository;

	private static final String DESCRICAO = "Feijoada";

	@Before
	public void setUp() throws Exception {
		PratoEntity prato = new PratoEntity();
		prato.setDescricao(DESCRICAO);
		prato.setReceita("Coloca o feijão dentro da panela e o encara profundamente até que ele fique constrangido e se faça sozinho");
		prato.setObservacao("Isso é um teste, viu?");

		this.pratoRepository.save(prato);
	}

	@After
	public final void tearDown() {
		this.pratoRepository.deleteAll();
	}

	@Test
	public void testBuscarPorDescricao() {
		PratoEntity prato = this.pratoRepository.findByDescricao(DESCRICAO);

		assertEquals(DESCRICAO, prato.getDescricao());
	}

}
