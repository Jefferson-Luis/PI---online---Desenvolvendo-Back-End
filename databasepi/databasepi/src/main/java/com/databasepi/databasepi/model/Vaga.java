package com.databasepi.databasepi.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotEmpty;

@Entity
public class Vaga implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long codigo;

	@NotEmpty
	private String quarto;

	@NotEmpty
	private String descricao;

	@NotEmpty
	private String data;

	@NotEmpty
	private String diaria;

	// Isso permite que ao deletar uma vaga eu delete todos os dependentes
	@OneToMany(mappedBy = "vaga", cascade = CascadeType.REMOVE)
	private List<Hospede> hospedes;

	public long getCodigo() {
		return codigo;
	}

	public void setCodigo(long codigo) {
		this.codigo = codigo;
	}

	public String getQuarto() {
		return quarto;
	}

	public void setQuarto(String quarto) {
		this.quarto = quarto;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

	public String getDiaria() {
		return diaria;
	}

	public void setDiaria(String diaria) {
		this.diaria = diaria;
	}

}
