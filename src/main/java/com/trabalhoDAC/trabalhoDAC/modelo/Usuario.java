/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.trabalhoDAC.trabalhoDAC.modelo;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

/**
 *
 * @author guilherme
 *
 */
@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class Usuario implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.TABLE)
	private Long ID;
	private static final long serialVersionUID = 1L;
	private String nome;
	private String matricula;
	@ManyToMany(cascade = CascadeType.ALL)
	@JoinTable(name = "usuario_papel", joinColumns = @JoinColumn(name = "usuario_ID"), inverseJoinColumns = @JoinColumn(name = "papel_ID"))
	private Set<Papel> papel;
	private String senha;
	private boolean autorizado;

	public Usuario(String nome, String matricula, String senha, boolean autorizado) {
		this.nome = nome;
		this.matricula = matricula;
		this.senha = senha;
		this.autorizado = autorizado;
	}

	public Usuario() {
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getMatricula() {
		return matricula;
	}

	public void setMatricula(String matricula) {
		this.matricula = matricula;
	}

	public Set<Papel> getPapel() {
		return papel;
	}

	public void setPapel(Set<Papel> papel) {
		this.papel = papel;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public boolean isAutorizado() {
		return autorizado;
	}

	public void setAutorizado(boolean autorizado) {
		this.autorizado = autorizado;
	}

	public Long getID() {
		return ID;
	}

	public void setID(Long ID) {
		this.ID = ID;
	}
}
