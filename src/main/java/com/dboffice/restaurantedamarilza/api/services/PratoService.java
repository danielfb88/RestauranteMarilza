package com.dboffice.restaurantedamarilza.api.services;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.dboffice.restaurantedamarilza.api.entities.PratoEntity;

public interface PratoService {
	/**
	 * Buscar por id
	 * 
	 * @param id
	 * @return
	 */
	Optional<PratoEntity> findById(Long id);

	/**
	 * Buscar Por descricao
	 * 
	 * @param descricao
	 * @return
	 */
	Optional<PratoEntity> findByDescricao(String descricao);

	/**
	 * Busca todos
	 * 
	 * @return
	 */
	List<PratoEntity> findAll();

	/**
	 * Busca todos
	 * 
	 * @param pageable
	 * @return
	 */
	Page<PratoEntity> findAll(Pageable pageable);

	/**
	 * Salvar prato
	 * 
	 * @param pratoEntity
	 * @return
	 */
	PratoEntity save(PratoEntity pratoEntity);

	/**
	 * Remove um prato da base de dados.
	 * 
	 * @param id
	 */
	void delete(Long id);
}
