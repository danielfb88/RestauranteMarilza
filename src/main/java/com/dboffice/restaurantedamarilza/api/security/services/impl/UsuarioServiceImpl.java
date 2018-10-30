package com.dboffice.restaurantedamarilza.api.security.services.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dboffice.restaurantedamarilza.api.security.entities.UsuarioEntity;
import com.dboffice.restaurantedamarilza.api.security.repositories.UsuarioRepository;
import com.dboffice.restaurantedamarilza.api.security.services.UsuarioService;

@Service
public class UsuarioServiceImpl implements UsuarioService {

	@Autowired
	private UsuarioRepository usuarioRepository;

	@Override
	public Optional<UsuarioEntity> buscaPorEmail(String email) {
		return Optional.ofNullable(this.usuarioRepository.findByEmail(email));
	}
}
