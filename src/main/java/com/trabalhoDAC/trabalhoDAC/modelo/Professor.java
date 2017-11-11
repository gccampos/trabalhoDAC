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

    private ArrayList<String> areaAtuacao;
    @OneToMany
    private List<Projeto> projetos;

    public Professor(String nome, String cpf, String endereco, String telefone, String matricula, String login, String senha, boolean autorizado) {
        super(nome, cpf, endereco, telefone, matricula, login, senha, autorizado);
        this.projetos= new ArrayList<>();
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
