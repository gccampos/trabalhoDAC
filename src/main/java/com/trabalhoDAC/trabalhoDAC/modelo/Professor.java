/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.trabalhoDAC.trabalhoDAC.modelo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.OneToMany;

/**
 *
 * @author guilherme
 */
@Entity
public class Professor extends Usuario implements Serializable {

	private static final long serialVersionUID = 1L;
	private ArrayList<String> areaAtuacao;
	@OneToMany
	private List<Projeto> projetos;

	public Professor() {
		super();
	}

	public Professor(String nome, String matricula, String senha, boolean autorizado) {
		super(nome, matricula, senha, autorizado);
		this.projetos = new ArrayList<>();
	}

	public List<String> getAreaAtuacao() {
		return areaAtuacao;
	}

	public void setAreaAtuacao(ArrayList<String> areaAtuacao) {
		this.areaAtuacao = areaAtuacao;
	}

	public List<Projeto> getProjetos() {
		return projetos;
	}

	public void setProjetos(List<Projeto> projetos) {
		this.projetos = projetos;
	}

}
