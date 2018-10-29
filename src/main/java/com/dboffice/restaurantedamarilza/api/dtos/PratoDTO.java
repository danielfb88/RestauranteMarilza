package com.dboffice.restaurantedamarilza.api.dtos;

import java.util.Optional;

public class PratoDTO {
	private Long id;
	private String descricao;
	private Optional<String> receita = Optional.empty();
	private Optional<String> observacao = Optional.empty();

	public PratoDTO() {
		super();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Optional<String> getReceita() {
		return receita;
	}

	public void setReceita(Optional<String> receita) {
		this.receita = receita;
	}

	public Optional<String> getObservacao() {
		return observacao;
	}

	public void setObservacao(Optional<String> observacao) {
		this.observacao = observacao;
	}

	@Override
	public String toString() {
		return "PratoDTO [id=" + id + ", descricao=" + descricao + ", receita=" + receita + ", observacao=" + observacao + "]";
	}

}
