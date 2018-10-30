package com.dboffice.restaurantedamarilza.api.security;

import java.util.ArrayList;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import com.dboffice.restaurantedamarilza.api.enums.PerfilEnum;
import com.dboffice.restaurantedamarilza.api.security.entities.UsuarioEntity;

public class JwtUserFactory {
	/**
	 * Converte e gera um JwtUser com base nos dados de um usuario.
	 * 
	 * @param usuarioEntity
	 * @return JwtUser
	 */
	public static JwtUser create(UsuarioEntity usuarioEntity) {
		return new JwtUser(usuarioEntity.getId(), usuarioEntity.getEmail(), usuarioEntity.getSenha(), mapToGrantedAuthorities(usuarioEntity.getPerfil()));
	}

	/**
	 * Converte o perfil do usuário para o formato utilizado pelo Spring Security.
	 * 
	 * @param perfilEnum
	 * @return List<GrantedAuthority>
	 */
	private static List<GrantedAuthority> mapToGrantedAuthorities(PerfilEnum perfilEnum) {
		List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();

		authorities.add(new SimpleGrantedAuthority(perfilEnum.toString()));

		return authorities;
	}
}
