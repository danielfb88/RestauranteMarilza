package com.dboffice.restaurantedamarilza.api.security.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import com.dboffice.restaurantedamarilza.api.security.entities.UsuarioEntity;

@Transactional(readOnly = true)
public interface UsuarioRepository extends JpaRepository<UsuarioEntity, Long> {

	UsuarioEntity findByEmail(String email);
}
