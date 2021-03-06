/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.trabalhoDAC.trabalhoDAC.modelo;

import java.io.File;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

/**
 *
 * @author guilherme
 */
@Entity
public class Projeto implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue
	private Long ID;
	private String titulo;
	@ManyToOne
	private Disciplina disciplina;
	private Double nota;
	private String resumo;
	private String cronograma;
	private ArrayList<String> bibliografia;
	@ManyToOne
	private Professor orientador;
	@ElementCollection
	private List<Aluno> alunos;
	@ElementCollection
	private List<File> versoesParciais;
	private File versaoFinal;

	public List<Aluno> getAlunos() {
		return alunos;
	}

	public void setAlunos(ArrayList<Aluno> alunos) {
		this.alunos = alunos;
	}

	public List<File> getVersoesParciais() {
		return this.versoesParciais;
	}

	public Projeto(String titulo, Disciplina disciplina, Double nota, String resumo, String cronograma,
			Professor orientador) {
		this.titulo = titulo;
		this.disciplina = disciplina;
		this.resumo = resumo;
		this.nota = nota;
		this.cronograma = cronograma;
		this.orientador = orientador;
		this.alunos = new ArrayList<Aluno>(2);
	}

	public Projeto() {
		this.bibliografia = new ArrayList<>();
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public Disciplina getDisciplina() {
		return disciplina;
	}

	public void setDisciplina(Disciplina disciplina) {
		this.disciplina = disciplina;
	}

	public double getNota() {
		return nota.doubleValue();
	}

	public void setNota(Double nota) {
		this.nota = nota;
	}

	public String getResumo() {
		return resumo;
	}

	public void setResumo(String resumo) {
		this.resumo = resumo;
	}

	public String getCronograma() {
		return cronograma;
	}

	public void setCronograma(String cronograma) {
		this.cronograma = cronograma;
	}

	public List<String> getBibliografia() {
		return bibliografia;
	}

	public Long getID() {
		return ID;
	}

	public void setID(Long ID) {
		this.ID = ID;
	}

	public File getVersaoFinal() {
		return versaoFinal;
	}

	public void setVersaoFinal(File versaoFinal) {
		this.versaoFinal = versaoFinal;
	}

	public Professor getOrientador() {
		return this.orientador;
	}
}
