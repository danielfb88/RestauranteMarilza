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

import com.dboffice.restaurantedamarilza.api.entities.FornecedorEntity;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("test")
public class FornecedorRepositoryTest {

    @Autowired
    private FornecedorRepository fornecedorRepository;

    private static final String DESCRICAO = "Atacadão";

    @Before
    public void setUp() throws Exception {
	FornecedorEntity fornecedor = new FornecedorEntity();
	fornecedor.setDescricao(DESCRICAO);
	fornecedor.setEndereco("Cabula");
	fornecedor.setObservacao("Isso é um teste, viu?");

	this.fornecedorRepository.save(fornecedor);
    }

    @After
    public final void tearDown() {
	this.fornecedorRepository.deleteAll();
    }

    @Test
    public void testBuscarPorDescricao() {
	FornecedorEntity fornecedor = this.fornecedorRepository.findByDescricao(DESCRICAO);

	assertEquals(DESCRICAO, fornecedor.getDescricao());
    }

}
