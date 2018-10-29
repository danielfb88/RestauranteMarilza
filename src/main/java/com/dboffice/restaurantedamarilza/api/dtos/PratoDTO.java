package com.dboffice.restaurantedamarilza.api.dtos;

public class PratoDTO {
	private Long id;
	private String descricao;
	private String receita;
	private String observacao;

	public PratoDTO() {
		super();
	}

	public PratoDTO(Long id, String descricao, String receita, String observacao) {
		super();
		this.id = id;
		this.descricao = descricao;
		this.receita = receita;
		this.observacao = observacao;
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

	public String getReceita() {
		return receita;
	}

	public void setReceita(String receita) {
		this.receita = receita;
	}

	public String getObservacao() {
		return observacao;
	}

	public void setObservacao(String observacao) {
		this.observacao = observacao;
	}

	@Override
	public String toString() {
		return "PratoDTO [id=" + id + ", descricao=" + descricao + ", receita=" + receita + ", observacao=" + observacao + "]";
	}

}
