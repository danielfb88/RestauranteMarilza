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
@Table(name = "tb_fornecedor")
public class FornecedorEntity implements Serializable {
    private static final long serialVersionUID = 5029446256019796420L;

    private Long id;
    private String descricao;
    private String observacao;
    private String endereco;
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

    public String getEndereco() {
	return endereco;
    }

    public void setEndereco(String endereco) {
	this.endereco = endereco;
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
	return "FornecedorEntity [id=" + id + ", descricao=" + descricao + ", observacao=" + observacao + ", endereco=" + endereco + ", dataCriacao=" + dataCriacao + ", dataAtualizacao=" + dataAtualizacao + "]";
    }

}
