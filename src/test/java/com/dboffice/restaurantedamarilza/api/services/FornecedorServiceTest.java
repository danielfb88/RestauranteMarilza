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

import com.dboffice.restaurantedamarilza.api.entities.FornecedorEntity;
import com.dboffice.restaurantedamarilza.api.repositories.FornecedorRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("test")
public class FornecedorServiceTest {

    @MockBean
    private FornecedorRepository fornecedorRepository;

    @Autowired
    private FornecedorService fornecedorService;

    @Before
    public void setUp() throws Exception {
	BDDMockito.given(this.fornecedorRepository.findOne(Mockito.anyLong())).willReturn(new FornecedorEntity());
	BDDMockito.given(this.fornecedorRepository.findByDescricao(Mockito.anyString())).willReturn(new FornecedorEntity());
	BDDMockito.given(this.fornecedorRepository.save(Mockito.any(FornecedorEntity.class))).willReturn(new FornecedorEntity());
    }

    @Test
    public void testBuscarPorId() {
	Optional<FornecedorEntity> fornecedorEntity = this.fornecedorService.findById(new Long(1));

	assertTrue(fornecedorEntity.isPresent());
    }

    @Test
    public void testBuscarPorDescricao() {
	Optional<FornecedorEntity> fornecedorEntity = this.fornecedorService.findByDescricao("Feijoada");

	assertTrue(fornecedorEntity.isPresent());
    }

    @Test
    public void testPersistirEmpresa() {
	FornecedorEntity fornecedorEntity = this.fornecedorService.save(new FornecedorEntity());

	assertNotNull(fornecedorEntity);
    }

}
