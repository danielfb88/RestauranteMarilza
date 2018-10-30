package com.dboffice.restaurantedamarilza.api.controller;

import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
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
		Optional<PratoEntity> pratoEntity = pratoService.findByDescricao(descricao);

		if (!pratoEntity.isPresent()) {
			log.info("Prato não encontrado para a descriçạo: {}", descricao);
			response.getErrors().add("Prato não encontrado para a descrição '" + descricao + "'");
			return ResponseEntity.badRequest().body(response);
		}

		response.setData(this.parseDTO(pratoEntity.get()));
		return ResponseEntity.ok(response);
	}

	/**
	 * Retorna todos
	 * 
	 * @return
	 */
	@GetMapping
	public ResponseEntity<Response<List<PratoDTO>>> findAll() {
		log.info("Buscando todos os pratos");
		Response<List<PratoDTO>> response = new Response<List<PratoDTO>>();

		List<PratoEntity> listPratoEntity = this.pratoService.findAll();
		List<PratoDTO> listPratoDTO = new ArrayList<PratoDTO>();
		for (PratoEntity pratoEntity : listPratoEntity) {
			listPratoDTO.add(this.parseDTO(pratoEntity));
		}

		response.setData(listPratoDTO);
		return ResponseEntity.ok(response);
	}

	/**
	 * Retorna uma prato por id.
	 * 
	 * @param prato
	 * @return ResponseEntity<Response<PratoDto>>
	 */
	@GetMapping(value = "/{id}")
	public ResponseEntity<Response<PratoDTO>> buscarPorId(@PathVariable("id") Long id) {
		log.info("Buscando prato por id: {}", id);
		Response<PratoDTO> response = new Response<PratoDTO>();
		Optional<PratoEntity> pratoEntity = pratoService.findById(id);

		if (!pratoEntity.isPresent()) {
			log.info("Prato não encontrado para o id: {}", id);
			response.getErrors().add("Prato não encontrado para o id '" + id + "'");
			return ResponseEntity.badRequest().body(response);
		}

		response.setData(this.parseDTO(pratoEntity.get()));
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

		PratoEntity pratoEntity = parseEntity(pratoDTO);
		this.pratoService.save(pratoEntity);

		response.setData(this.parseDTO(pratoEntity));
		return ResponseEntity.ok(response);
	}

	/**
	 * Atualiza os dados de um funcionário.
	 * 
	 * @param id
	 * @param pratoDTO
	 * @param result
	 * @return ResponseEntity<Response<PratoDTO>>
	 * @throws NoSuchAlgorithmException
	 */
	@PutMapping(value = "/{id}")
	public ResponseEntity<Response<PratoDTO>> atualizar(@PathVariable("id") Long id, @Valid @RequestBody PratoDTO pratoDTO, BindingResult result) throws NoSuchAlgorithmException {
		log.info("Atualizando prato: {}", pratoDTO.toString());
		Response<PratoDTO> response = new Response<PratoDTO>();

		Optional<PratoEntity> optPratoEntity = this.pratoService.findById(id);
		if (!optPratoEntity.isPresent()) {
			result.addError(new ObjectError("prato", "Prato não encontrado."));
		}

		PratoEntity pratoEntity = optPratoEntity.get();

		// Verificando se já existe outro com a mesma descrição
		if (!pratoEntity.getDescricao().equals(pratoDTO.getDescricao())) {
			this.pratoService.findByDescricao(pratoDTO.getDescricao()).ifPresent(func -> result.addError(new ObjectError("descricao", "Descrição já existente.")));

			pratoEntity.setDescricao(pratoDTO.getDescricao());
		}

		pratoEntity.setReceita(null);
		pratoDTO.getReceita().ifPresent(receita -> pratoEntity.setReceita(receita));

		pratoEntity.setObservacao(null);
		pratoDTO.getObservacao().ifPresent(observacao -> pratoEntity.setObservacao(observacao));

		if (result.hasErrors()) {
			log.error("Erro validando prato: {}", result.getAllErrors());
			result.getAllErrors().forEach(error -> response.getErrors().add(error.getDefaultMessage()));
			return ResponseEntity.badRequest().body(response);
		}

		this.pratoService.save(pratoEntity);
		response.setData(this.parseDTO(pratoEntity));

		return ResponseEntity.ok(response);
	}

	/**
	 * Remove por id.
	 * 
	 * @param id
	 * @return
	 */
	@DeleteMapping(value = "/{id}")
	@PreAuthorize("hasAnyRole('ADMIN')")
	public ResponseEntity<Response<String>> remover(@PathVariable("id") Long id) {
		log.info("Removendo um prato: {}", id);
		Response<String> response = new Response<String>();
		Optional<PratoEntity> pratoEntity = this.pratoService.findById(id);

		if (!pratoEntity.isPresent()) {
			log.info("Erro ao remover devido ao prato ID: {} ser inválido.", id);
			response.getErrors().add("Erro ao remover prato. Registro não encontrado para o id " + id);
			return ResponseEntity.badRequest().body(response);
		}

		this.pratoService.delete(id);
		return ResponseEntity.ok(new Response<String>());
	}

	/**
	 * Verifica se o prato está cadastrada
	 * 
	 * @param pratoDTO
	 * @param result
	 */
	private void validarDadosExistentes(PratoDTO pratoDTO, BindingResult result) {
		this.pratoService.findByDescricao(pratoDTO.getDescricao()).ifPresent(func -> result.addError(new ObjectError("prato", "Descrição já existente.")));
	}

	/**
	 * Popula um DTO com os dados de uma entity.
	 * 
	 * @param pratoEntity
	 * @return PratoDTO
	 */
	private PratoDTO parseDTO(PratoEntity pratoEntity) {
		PratoDTO pratoDTO = new PratoDTO();
		pratoDTO.setId(pratoEntity.getId());
		pratoDTO.setDescricao(pratoEntity.getDescricao());
		pratoDTO.setReceita(pratoEntity.getReceitaOpt());
		pratoDTO.setObservacao(pratoEntity.getObservacaoOpt());

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
	private PratoEntity parseEntity(PratoDTO pratoDTO) throws NoSuchAlgorithmException {
		PratoEntity pratoEntity = new PratoEntity();
		pratoEntity.setId(pratoDTO.getId());
		pratoEntity.setDescricao(pratoDTO.getDescricao());
		pratoEntity.setReceita(pratoDTO.getReceita().get());
		pratoEntity.setObservacao(pratoDTO.getObservacao().get());

		return pratoEntity;
	}

}
