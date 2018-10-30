package com.dboffice.restaurantedamarilza.api.security.services.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.dboffice.restaurantedamarilza.api.security.JwtUserFactory;
import com.dboffice.restaurantedamarilza.api.security.entities.UsuarioEntity;
import com.dboffice.restaurantedamarilza.api.security.services.UsuarioService;

@Service
public class JwtUserDetailsServiceImpl implements UserDetailsService {

	@Autowired
	private UsuarioService usuarioService;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Optional<UsuarioEntity> usuarioEntity = usuarioService.buscaPorEmail(username);

		if (usuarioEntity.isPresent()) {
			return JwtUserFactory.create(usuarioEntity.get());
		}

		throw new UsernameNotFoundException("Email não encontrado.");
	}
}
