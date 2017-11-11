/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.trabalhoDAC.trabalhoDAC.modelo;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

/**
 *
 * @author guilherme
 */
@Entity
public class Aluno extends Usuario implements Serializable {

    @OneToOne
    private Disciplina disciplina;
    @OneToOne
    private Projeto projetoInscrito;
    @OneToMany
    private List<Projeto> projetosInteressado;

    public Aluno(String nome, String cpf, String endereco, String telefone, String matricula, String login, String senha, boolean autorizado) {
        super(nome, cpf, endereco, telefone, matricula, login, senha, autorizado);
    }

    public Aluno() {
        super();
    }

    public Disciplina getDisciplina() {
        return disciplina;
    }

    public void setDisciplina(Disciplina disciplina) {
        this.disciplina = disciplina;
    }

    public Projeto getProjetoInscrito() {
        return projetoInscrito;
    }

    public void setProjetoInscrito(Projeto projetoInscrito) {
        this.projetoInscrito = projetoInscrito;
    }

    public List<Projeto> getProjetosInteressado() {
        return projetosInteressado;
    }

    public void setProjetosInteressado(List<Projeto> projetosInteressado) {
        this.projetosInteressado = projetosInteressado;
    }

}
