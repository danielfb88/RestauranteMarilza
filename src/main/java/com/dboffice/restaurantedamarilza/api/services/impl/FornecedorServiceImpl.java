package com.dboffice.restaurantedamarilza.api.services.impl;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.dboffice.restaurantedamarilza.api.entities.FornecedorEntity;
import com.dboffice.restaurantedamarilza.api.repositories.FornecedorRepository;
import com.dboffice.restaurantedamarilza.api.services.FornecedorService;

@Service
public class FornecedorServiceImpl implements FornecedorService {
    private static final Logger log = LoggerFactory.getLogger(FornecedorServiceImpl.class);

    @Autowired
    private FornecedorRepository fornecedorRepository;

    @Override
    public Optional<FornecedorEntity> findById(Long id) {
	log.info("Buscando um fornecedor para a ID {}", id);

	return Optional.ofNullable(fornecedorRepository.findOne(id));
    }

    @Override
    public Optional<FornecedorEntity> findByDescricao(String descricao) {
	log.info("Buscando um fornecedor para a descrição {}", descricao);

	return Optional.ofNullable(fornecedorRepository.findByDescricao(descricao));
    }

    @Override
    public FornecedorEntity save(FornecedorEntity fornecedorEntity) {
	log.info("Persistindo fornecedor: {}", fornecedorEntity);

	return this.fornecedorRepository.save(fornecedorEntity);
    }

    public void delete(Long id) {
	log.info("Removendo o fornecedor ID {}", id);
	this.fornecedorRepository.delete(id);
    }

    @Override
    public List<FornecedorEntity> findAll() {
	log.info("Buscando todos os fornecedors.");

	return this.fornecedorRepository.findAll();
    }

    @Override
    public Page<FornecedorEntity> findAll(Pageable pageable) {
	log.info("Buscando todos os fornecedors paginados.");

	return this.fornecedorRepository.findAll(pageable);
    }

}
