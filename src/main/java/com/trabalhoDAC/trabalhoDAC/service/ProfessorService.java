/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.trabalhoDAC.trabalhoDAC.service;

import com.trabalhoDAC.trabalhoDAC.DAO.ProfessorDAO;
import com.trabalhoDAC.trabalhoDAC.modelo.Papel;
import com.trabalhoDAC.trabalhoDAC.modelo.Professor;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

/**
 *
 * @author guilherme
 */
@Service
public class ProfessorService {

    @Autowired
    private ProfessorDAO professorDAO;
    @Autowired
    private PapelService papelService;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    public Professor buscarPorNome(String nome) {
        return professorDAO.buscarPorNome(nome);
    }

    public Professor buscarPorId(Long id) {
        return professorDAO.findOne(id);
    }

    public List<Professor> listarTodos() {
        return professorDAO.findAll();

    }

    public Professor salvar(Professor professor) {
        professor.setSenha(bCryptPasswordEncoder.encode(professor.getSenha()));
        Papel papel = papelService.buscarPorPapel("PROFESSOR");
        professor.setPapel(new HashSet<Papel>(Arrays.asList(papel)));
        professorDAO.saveAndFlush(professor);
        return professor;
    }
}
