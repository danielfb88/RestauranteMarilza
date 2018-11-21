package com.dboffice.restaurantedamarilza.api.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import com.dboffice.restaurantedamarilza.api.entities.FornecedorEntity;

public interface FornecedorRepository extends JpaRepository<FornecedorEntity, Long> {

    @Transactional(readOnly = true)
    FornecedorEntity findByDescricao(String descricao);

}
