package com.dboffice.restaurantedamarilza.api.security.services;

import java.util.Optional;

import com.dboffice.restaurantedamarilza.api.security.entities.UsuarioEntity;

public interface UsuarioService {

	Optional<UsuarioEntity> buscaPorEmail(String email);

}
