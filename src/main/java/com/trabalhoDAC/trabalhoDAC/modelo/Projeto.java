/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.trabalhoDAC.trabalhoDAC.modelo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

/**
 *
 * @author guilherme
 */
@Entity
public class Projeto implements Serializable {

    @Id
    @GeneratedValue
    private Long ID;
    private String titulo;
    @ManyToOne
    private Disciplina disciplina;
    private double nota;
    private String resumo;
    private String cronograma;
    private ArrayList<String> bibliografia;
    @ManyToOne
    private Professor orientador;
    @OneToOne
    @Column(unique = true)
    private Aluno aluno1;
    @OneToOne
    private Aluno aluno2;
    

    public Projeto(String titulo, Disciplina disciplina, String resumo, String cronograma, Professor orientador) {
        this.titulo = titulo;
        this.disciplina = disciplina;
        this.resumo = resumo;
        this.cronograma = cronograma;
        this.orientador = orientador;
    }

    public Aluno getAluno1() {
        return aluno1;
    }

    public void setAluno1(Aluno aluno1) {
        this.aluno1 = aluno1;
    }

    public Aluno getAluno2() {
        return aluno2;
    }

    public void setAluno2(Aluno aluno2) {
        this.aluno2 = aluno2;
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
        return nota;
    }

    public void setNota(double nota) {
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
}
