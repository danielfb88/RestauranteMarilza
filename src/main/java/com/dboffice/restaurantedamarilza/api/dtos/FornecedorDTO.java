package com.dboffice.restaurantedamarilza.api.dtos;

import java.util.Optional;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

public class FornecedorDTO {
    private Long id;
    private String descricao;
    private String endereco;
    private Optional<String> observacao = Optional.empty();

    public FornecedorDTO() {
	super();
    }

    public Long getId() {
	return id;
    }

    public void setId(Long id) {
	this.id = id;
    }

    @NotEmpty(message = "Descrição não pode ser vazio.")
    @Length(min = 3, max = 100, message = "Descrião deve conter entre 3 e 100 caracteres.")
    public String getDescricao() {
	return descricao;
    }

    public void setDescricao(String descricao) {
	this.descricao = descricao;
    }

    public Optional<String> getObservacao() {
	return observacao;
    }

    public void setObservacao(Optional<String> observacao) {
	this.observacao = observacao;
    }

    public String getEndereco() {
	return endereco;
    }

    public void setEndereco(String endereco) {
	this.endereco = endereco;
    }

    @Override
    public String toString() {
	return "FornecedorDTO [id=" + id + ", descricao=" + descricao + ", endereco=" + endereco + ", observacao=" + observacao + "]";
    }

}
