/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.trabalhoDAC.trabalhoDAC.service;

import com.trabalhoDAC.trabalhoDAC.DAO.DisciplinaDAO;
import com.trabalhoDAC.trabalhoDAC.modelo.Disciplina;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author guilherme
 */
@Service
public class DisciplinaService {

    @Autowired
    private DisciplinaDAO disciplinaDAO;

    public Disciplina buscarPorNome(String nome) {
        return disciplinaDAO.buscarPorNome(nome);
    }

    public Disciplina buscarPorId(Long id) {
        return disciplinaDAO.findOne(id);
    }

    public List<Disciplina> listarTodas() {
        return disciplinaDAO.findAll();
    }

    public Disciplina salvar(Disciplina disciplina) {
        return disciplinaDAO.save(disciplina);
    }

}
