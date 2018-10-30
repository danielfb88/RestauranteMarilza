package com.dboffice.restaurantedamarilza.api.services.impl;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.dboffice.restaurantedamarilza.api.entities.PratoEntity;
import com.dboffice.restaurantedamarilza.api.repositories.PratoRepository;
import com.dboffice.restaurantedamarilza.api.services.PratoService;

@Service
public class PratoServiceImpl implements PratoService {
	private static final Logger log = LoggerFactory.getLogger(PratoServiceImpl.class);

	@Autowired
	private PratoRepository pratoRepository;

	@Override
	public Optional<PratoEntity> findById(Long id) {
		log.info("Buscando um prato para a ID {}", id);

		return Optional.ofNullable(pratoRepository.findOne(id));
	}

	@Override
	public Optional<PratoEntity> findByDescricao(String descricao) {
		log.info("Buscando um prato para a descrição {}", descricao);

		return Optional.ofNullable(pratoRepository.findByDescricao(descricao));
	}

	@Override
	public PratoEntity save(PratoEntity pratoEntity) {
		log.info("Persistindo prato: {}", pratoEntity);

		return this.pratoRepository.save(pratoEntity);
	}

	public void delete(Long id) {
		log.info("Removendo o prato ID {}", id);
		this.pratoRepository.delete(id);
	}

	@Override
	public List<PratoEntity> findAll() {
		log.info("Buscando todos os pratos.");

		return this.pratoRepository.findAll();
	}

	@Override
	public Page<PratoEntity> findAll(Pageable pageable) {
		log.info("Buscando todos os pratos paginados.");

		return this.pratoRepository.findAll(pageable);
	}

}
