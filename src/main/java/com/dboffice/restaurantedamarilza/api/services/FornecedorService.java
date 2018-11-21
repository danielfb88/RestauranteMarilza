package com.dboffice.restaurantedamarilza.api.services;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.dboffice.restaurantedamarilza.api.entities.FornecedorEntity;

public interface FornecedorService {
    /**
     * Buscar por id
     * 
     * @param id
     * @return
     */
    Optional<FornecedorEntity> findById(Long id);

    /**
     * Buscar Por descricao
     * 
     * @param descricao
     * @return
     */
    Optional<FornecedorEntity> findByDescricao(String descricao);

    /**
     * Busca todos
     * 
     * @return
     */
    List<FornecedorEntity> findAll();

    /**
     * Busca todos
     * 
     * @param pageable
     * @return
     */
    Page<FornecedorEntity> findAll(Pageable pageable);

    /**
     * Salvar fornecedor
     * 
     * @param fornecedorEntity
     * @return
     */
    FornecedorEntity save(FornecedorEntity fornecedorEntity);

    /**
     * Remove um fornecedor da base de dados.
     * 
     * @param id
     */
    void delete(Long id);
}
