/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.trabalhoDAC.trabalhoDAC.modelo;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Entity;

/**
 *
 * @author guilherme
 */
@Entity
public class Professor extends Usuario implements Serializable {
    private List<String> areaAtuacao;
    private Projeto projeto;

    public Professor(String nome, String endereco, String telefone, String matricula, String papel, String login, String senha, boolean autorizado) {
        super(nome, endereco, telefone, matricula, papel, login, senha, autorizado);
    }

    public List<String> getAreaAtuacao() {
        return areaAtuacao;
    }

    public void setAreaAtuacao(List<String> areaAtuacao) {
        this.areaAtuacao = areaAtuacao;
    }

    public Projeto getProjeto() {
        return projeto;
    }

    public void setProjeto(Projeto projeto) {
        this.projeto = projeto;
    }
    
    
}
