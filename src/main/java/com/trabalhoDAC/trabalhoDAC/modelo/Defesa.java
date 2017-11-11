/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.trabalhoDAC.trabalhoDAC.modelo;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import org.hibernate.annotations.Type;

/**
 *
 * @author guilherme
 */
@Entity
public class Defesa implements Serializable {

    @Id
    @GeneratedValue
    private Long ID;

    @Type(type = "date")
    private Date data;
    @Type(type = "time")
    private Date hora;
    private List<Professor> convidados;
    private List<Professor> aceitos;
    private String sala;
    private Projeto projeto;
    private boolean validada;

    public Defesa(Date data, Date hora, List<Professor> banca, String sala, Projeto projeto, boolean validada) {
        this.data = data;
        this.convidados = banca;
        this.sala = sala;
        this.projeto = projeto;
        this.hora = hora;
        this.validada = validada;
    }

    public Long getID() {
        return ID;
    }

    public void setID(Long ID) {
        this.ID = ID;
    }

}
