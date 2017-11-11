/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.trabalhoDAC.trabalhoDAC.controller;

import com.trabalhoDAC.trabalhoDAC.modelo.Aluno;
import com.trabalhoDAC.trabalhoDAC.modelo.Disciplina;
import com.trabalhoDAC.trabalhoDAC.modelo.Professor;
import com.trabalhoDAC.trabalhoDAC.modelo.Projeto;
import com.trabalhoDAC.trabalhoDAC.service.AlunoService;
import com.trabalhoDAC.trabalhoDAC.service.DisciplinaService;
import com.trabalhoDAC.trabalhoDAC.service.ProfessorService;
import com.trabalhoDAC.trabalhoDAC.service.ProjetoService;
import java.io.IOException;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author guilherme
 */
@Controller
public class CadastroProjetoController {

    @Autowired
    private ProjetoService projetoService;
    @Autowired
    private ProfessorService professorService;
    @Autowired
    private DisciplinaService disciplinaService;
    @Autowired
    private AlunoService alunoService;

    @GetMapping("cadastroProjeto")
    public ModelAndView serveCadastroProjeto() {
        ModelAndView modelAndView = new ModelAndView("cadastroProjeto");
        return modelAndView;
    }

    @Secured({"PROFESSOR", "ADMIN"})
    @PostMapping("/cadastroProjeto")
    public ModelAndView cadastroProjeto(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {
        boolean doisAlunos = false;
        response.setContentType("text/html;charset=UTF-8");
        String titulo = request.getParameter("titulo");
        String nomeDisciplina = request.getParameter("nomeDisciplina");
        String resumo = request.getParameter("resumo");
        String cronograma = request.getParameter("Cronograma");
        String nomeOrientador = request.getParameter("nomeOrientador");
        String nomeAluno1 = request.getParameter("nomeAluno1");
        String nomeAluno2 = request.getParameter("nomeAluno2");
        Aluno aluno1 = alunoService.buscarPorNome(nomeAluno1);
        Aluno aluno2 = new Aluno();
        Aluno[] alunos = new Aluno[2];
        alunos[0] = aluno1;
        if (nomeAluno2 != "") {
            aluno2 = alunoService.buscarPorNome(nomeAluno2);
            doisAlunos = true;
        }
        Disciplina disciplina = disciplinaService.buscarPorNome(nomeDisciplina);
        if (!aluno1.getDisciplina().equals(disciplina)) {
            return new ModelAndView("naoPode");
        }
        if (doisAlunos) {
            if (!aluno2.getDisciplina().equals(disciplina)) {
                return new ModelAndView("naoPode");
            }
        }
        Professor professor = professorService.buscarPorNome(nomeOrientador);
        Projeto projeto = new Projeto(titulo, disciplina, resumo, cronograma, professor);
        projeto.setAlunos(alunos);
        if (doisAlunos) {
            alunos[1] = aluno2;
            projeto.setAlunos(alunos);
        }
        aluno1.setProjetoInscrito(projeto);
        alunoService.salvar(aluno1);
        if (doisAlunos) {

            aluno2.setProjetoInscrito(projeto);
            alunoService.salvar(aluno2);
        }
        projetoService.salvar(projeto);

        return new ModelAndView("sucessoCadastroProjeto");

    }
}
