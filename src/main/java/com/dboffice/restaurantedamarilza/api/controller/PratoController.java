package com.dboffice.restaurantedamarilza.api.controller;

import java.security.NoSuchAlgorithmException;
import java.util.Optional;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dboffice.restaurantedamarilza.api.dtos.PratoDTO;
import com.dboffice.restaurantedamarilza.api.entities.PratoEntity;
import com.dboffice.restaurantedamarilza.api.response.Response;
import com.dboffice.restaurantedamarilza.api.services.PratoService;

@RestController
@RequestMapping("/api/prato")
@CrossOrigin(origins = "*")
public class PratoController {

	private static final Logger log = LoggerFactory.getLogger(PratoController.class);

	@Autowired
	private PratoService pratoService;

	/**
	 * Retorna uma prato dado uma descricao.
	 * 
	 * @param prato
	 * @return ResponseEntity<Response<PratoDto>>
	 */
	@GetMapping(value = "/descricao/{descricao}")
	public ResponseEntity<Response<PratoDTO>> buscarPorDescricao(@PathVariable("descricao") String descricao) {
		log.info("Buscando prato por descrição: {}", descricao);
		Response<PratoDTO> response = new Response<PratoDTO>();
		Optional<PratoEntity> pratoEntity = pratoService.buscarPorDescricao(descricao);

		if (!pratoEntity.isPresent()) {
			log.info("Prato não encontrado para a descriçạo: {}", descricao);
			response.getErrors().add("Prato não encontrado para a descrição '" + descricao + "'");
			return ResponseEntity.badRequest().body(response);
		}

		response.setData(this.converterParaDTO(pratoEntity.get()));
		return ResponseEntity.ok(response);
	}

	/**
	 * Cadastra um prato
	 * 
	 * @param pratoDTO
	 * @param result
	 * @return
	 * @throws NoSuchAlgorithmException
	 */
	@PostMapping
	public ResponseEntity<Response<PratoDTO>> salvar(@Valid @RequestBody PratoDTO pratoDTO, BindingResult result) throws NoSuchAlgorithmException {
		log.info("Cadastrando PF: {}", pratoDTO.toString());
		Response<PratoDTO> response = new Response<PratoDTO>();

		validarDadosExistentes(pratoDTO, result);

		if (result.hasErrors()) {
			log.error("Erro validando dados do prato: {}", result.getAllErrors());
			result.getAllErrors().forEach(error -> response.getErrors().add(error.getDefaultMessage()));
			return ResponseEntity.badRequest().body(response);
		}

		PratoEntity pratoEntity = converterParaEntity(pratoDTO);
		this.pratoService.salvar(pratoEntity);

		response.setData(this.converterParaDTO(pratoEntity));
		return ResponseEntity.ok(response);
	}

	/**
	 * Verifica se o prato está cadastrada
	 * 
	 * @param pratoDTO
	 * @param result
	 */
	private void validarDadosExistentes(PratoDTO pratoDTO, BindingResult result) {
		this.pratoService.buscarPorDescricao(pratoDTO.getDescricao()).ifPresent(func -> result.addError(new ObjectError("prato", "Descrição já existente.")));
	}

	/**
	 * Popula um DTO com os dados de uma entity.
	 * 
	 * @param pratoEntity
	 * @return PratoDTO
	 */
	private PratoDTO converterParaDTO(PratoEntity pratoEntity) {
		PratoDTO pratoDTO = new PratoDTO();
		pratoDTO.setId(pratoEntity.getId());
		pratoDTO.setDescricao(pratoEntity.getDescricao());
		pratoDTO.setReceita(pratoEntity.getReceita());
		pratoDTO.setObservacao(pratoEntity.getObservacao());

		return pratoDTO;
	}

	/**
	 * Converte os dados do DTO para Entity.
	 * 
	 * @param pratoDTO
	 * @param result
	 * @return Funcionario
	 * @throws NoSuchAlgorithmException
	 */
	private PratoEntity converterParaEntity(PratoDTO pratoDTO) throws NoSuchAlgorithmException {
		PratoEntity pratoEntity = new PratoEntity();
		pratoEntity.setId(pratoDTO.getId());
		pratoEntity.setDescricao(pratoDTO.getDescricao());
		pratoEntity.setReceita(pratoDTO.getReceita());
		pratoEntity.setObservacao(pratoDTO.getObservacao());

		return pratoEntity;
	}

}
