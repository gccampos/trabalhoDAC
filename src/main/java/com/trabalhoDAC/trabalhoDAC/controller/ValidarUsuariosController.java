/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.trabalhoDAC.trabalhoDAC.controller;

import com.trabalhoDAC.trabalhoDAC.modelo.Aluno;
import com.trabalhoDAC.trabalhoDAC.modelo.Professor;
import com.trabalhoDAC.trabalhoDAC.service.AlunoService;
import com.trabalhoDAC.trabalhoDAC.service.ProfessorService;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author guilherme
 */
@Controller
public class ValidarUsuariosController {
    
    @Autowired
    private ProfessorService professorService;
    @Autowired
    private AlunoService alunoService;
    
    @GetMapping("validarUsuarios")
    public ModelAndView serveValidarUsuarios() {
        ModelAndView modelAndView = new ModelAndView("validarUsuarios");
        List<Professor> professores = professorService.listarNaoAutorizados();
        List<Aluno> alunos = alunoService.listarNaoAutorizados();
        modelAndView.addObject("professores", professores);
        modelAndView.addObject("alunos", alunos);
        return modelAndView;
    }
    
    @PostMapping("validarAlunos")
    public ModelAndView validarAlunos(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {
        long alunoId = Long.parseLong(request.getParameter("alunoId"));
        Aluno aluno = alunoService.buscarPorId(alunoId);
        aluno.setAutorizado(true);
        alunoService.salvar(aluno);
        return new ModelAndView("validarUsuarios");
    }

    @PostMapping("validarProfessores")
    public ModelAndView validarProfessores(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {
        long professorId = Long.parseLong(request.getParameter("professorId"));
        Professor professor = professorService.buscarPorId(professorId);
        professor.setAutorizado(true);
        professorService.salvar(professor);
        return new ModelAndView("validarUsuarios");
    }
    
}
