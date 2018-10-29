package com.dboffice.restaurantedamarilza.api.services.impl;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
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
	public Optional<PratoEntity> buscarPorDescricao(String descricao) {
		log.info("Buscando um prato para a descrição {}", descricao);

		return Optional.ofNullable(pratoRepository.findByDescricao(descricao));
	}

	@Override
	public PratoEntity salvar(PratoEntity pratoEntity) {
		log.info("Persistindo prato: {}", pratoEntity);

		return this.pratoRepository.save(pratoEntity);
	}

}
