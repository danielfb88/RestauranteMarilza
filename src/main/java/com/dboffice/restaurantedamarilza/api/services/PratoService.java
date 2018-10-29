package com.dboffice.restaurantedamarilza.api.services;

import java.util.Optional;

import com.dboffice.restaurantedamarilza.api.entities.PratoEntity;

public interface PratoService {
	/**
	 * Buscar Por descricao
	 * 
	 * @param descricao
	 * @return
	 */
	Optional<PratoEntity> buscarPorDescricao(String descricao);

	/**
	 * Salvar prato
	 * 
	 * @param pratoEntity
	 * @return
	 */
	PratoEntity salvar(PratoEntity pratoEntity);
}
