package com.dboffice.restaurantedamarilza.api.entities;

import java.io.Serializable;
import java.util.Date;
import java.util.Optional;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "tb_prato")
public class PratoEntity implements Serializable {
	private static final long serialVersionUID = -3549635549813419660L;

	private Long id;
	private String descricao;
	private String receita;
	private String observacao;
	private Date dataCriacao;
	private Date dataAtualizacao;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Column(name = "descricao", nullable = false)
	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	@Column(name = "receita", nullable = true)
	public String getReceita() {
		return receita;
	}
	
	@Transient
	public Optional<String> getReceitaOpt() {
		return Optional.ofNullable(receita);
	}

	public void setReceita(String receita) {
		this.receita = receita;
	}

	@Column(name = "observacao", nullable = true)
	public String getObservacao() {
		return observacao;
	}
	
	@Transient
	public Optional<String> getObservacaoOpt() {
		return Optional.ofNullable(observacao);
	}

	public void setObservacao(String observacao) {
		this.observacao = observacao;
	}

	@Column(name = "data_criacao", nullable = false)
	public Date getDataCriacao() {
		return dataCriacao;
	}

	public void setDataCriacao(Date dataCriacao) {
		this.dataCriacao = dataCriacao;
	}

	@Column(name = "data_atualizacao", nullable = false)
	public Date getDataAtualizacao() {
		return dataAtualizacao;
	}

	public void setDataAtualizacao(Date dataAtualizacao) {
		this.dataAtualizacao = dataAtualizacao;
	}

	@PreUpdate
	public void preUpdate() {
		dataAtualizacao = new Date();
	}

	@PrePersist
	public void prePersist() {
		final Date atual = new Date();
		dataCriacao = atual;
		dataAtualizacao = atual;
	}

	@Override
	public String toString() {
		return "PratoEntity [id=" + id + ", descricao=" + descricao + ", receita=" + receita + ", observacao=" + observacao + ", dataCriacao=" + dataCriacao + ", dataAtualizacao=" + dataAtualizacao + "]";
	}

}
