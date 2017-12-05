package com.trabalhoDAC.trabalhoDAC.modelo;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.OneToOne;

@Entity
public class Orientacao extends Usuario implements Serializable {
	private static final long serialVersionUID = 1L;

	@OneToOne
	private Projeto projeto;

	@OneToOne
	private Professor orientador;

	public Projeto getProjeto() {
		return projeto;
	}

	public void setProjeto(Projeto projeto) {
		this.projeto = projeto;
	}

	public Professor getOrientador() {
		return orientador;
	}

	public void setOrientador(Professor orientador) {
		this.orientador = orientador;
	}

}
