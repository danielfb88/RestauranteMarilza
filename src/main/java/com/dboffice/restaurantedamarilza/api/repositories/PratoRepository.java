package com.dboffice.restaurantedamarilza.api.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import com.dboffice.restaurantedamarilza.api.entities.PratoEntity;

public interface PratoRepository extends JpaRepository<PratoEntity, Long> {
	
	@Transactional(readOnly = true)
	PratoEntity findByDescricao(String descricao);
}
