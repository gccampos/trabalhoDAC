/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.trabalhoDAC.trabalhoDAC.controller;

import com.trabalhoDAC.trabalhoDAC.modelo.Aluno;
import com.trabalhoDAC.trabalhoDAC.modelo.Professor;
import com.trabalhoDAC.trabalhoDAC.modelo.Projeto;
import com.trabalhoDAC.trabalhoDAC.service.AlunoService;
import com.trabalhoDAC.trabalhoDAC.service.ProfessorService;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
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
public class CadastroUsuarioController {

    @Autowired
    private ProfessorService professorService;
    @Autowired
    private AlunoService alunoService;

    @GetMapping("cadastroAluno")
    public ModelAndView serveCadastroAluno() {
        ModelAndView modelAndView = new ModelAndView("cadastroAluno");
        return modelAndView;
    }

    @GetMapping("cadastroProfessor")
    public ModelAndView serveCadastroProfessor() {
        ModelAndView modelAndView = new ModelAndView("cadastroProfessor");
        return modelAndView;
    }

    @PostMapping("/cadastroAluno")
    public ModelAndView cadastroAluno(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {

        response.setContentType("text/html;charset=UTF-8");
        String nome = request.getParameter("nome");
        String endereco = request.getParameter("endereco");
        String telefone = request.getParameter("telefone");
        String senha = request.getParameter("senha");
        String cpf = request.getParameter("cpf");
        String matricula = request.getParameter("matricula");
        String login = request.getParameter("login");
        String interesse1 = request.getParameter("interesse1");
        String interesse2 = request.getParameter("interesse2");
        String interesse3 = request.getParameter("interesse3");
        List<Projeto> interesses = new ArrayList<>();
//        interesses.add(interesse1);
//        interesses.add(interesse2);
//        interesses.add(interesse3);
        Aluno aluno = new Aluno(nome, cpf, endereco, telefone, matricula, login, senha, false);
        aluno.setProjetosInteressado(interesses);
        alunoService.salvarCadastro(aluno);
        return new ModelAndView("redirect:/sucessoCadastroUser");
    }

    @PostMapping("/cadastroAlunoAdm")
    public ModelAndView cadastroAlunoAdm(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {

        response.setContentType("text/html;charset=UTF-8");
        String nome = request.getParameter("nome");
        String endereco = request.getParameter("endereco");
        String telefone = request.getParameter("telefone");
        String senha = request.getParameter("senha");
        String cpf = request.getParameter("cpf");
        String matricula = request.getParameter("matricula");
        String login = request.getParameter("login");
        String interesse1 = request.getParameter("interesse1");
        String interesse2 = request.getParameter("interesse2");
        String interesse3 = request.getParameter("interesse3");
        List<Projeto> interesses = new ArrayList<>();
//        interesses.add(interesse1);
//        interesses.add(interesse2);
//        interesses.add(interesse3);
        Aluno aluno = new Aluno(nome, cpf, endereco, telefone, matricula, login, senha, true);
        aluno.setProjetosInteressado(interesses);
        alunoService.salvarCadastro(aluno);
        return new ModelAndView("redirect:/sucessoCadastroUser");
    }

    @PostMapping("/cadastroProfessorAdm")
    public ModelAndView cadastroProfessorAdm(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {

        response.setContentType("text/html;charset=UTF-8");
        String nome = request.getParameter("nome");
        String endereco = request.getParameter("endereco");
        String telefone = request.getParameter("telefone");
        String senha = request.getParameter("senha");
        String cpf = request.getParameter("cpf");
        String matricula = request.getParameter("matricula");
        String login = request.getParameter("login");
        String atuacao1 = request.getParameter("atuacao1");
        String atuacao2 = request.getParameter("atuacao2");
        String atuacao3 = request.getParameter("atuacao3");
        ArrayList<String> areaAtuacao = new ArrayList<String>();
        areaAtuacao.add(atuacao1);
        areaAtuacao.add(atuacao2);
        areaAtuacao.add(atuacao3);
        Professor professor = new Professor(nome, cpf, endereco, telefone, matricula, login, senha, true);
        professor.setAreaAtuacao(areaAtuacao);
        professorService.salvarCadastro(professor);
        return new ModelAndView("redirect:/sucessoCadastroUser");
    }

    @PostMapping("/cadastroProfessor")
    public ModelAndView cadastroProfessor(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {

        response.setContentType("text/html;charset=UTF-8");
        String nome = request.getParameter("nome");
        String endereco = request.getParameter("endereco");
        String telefone = request.getParameter("telefone");
        String senha = request.getParameter("senha");
        String cpf = request.getParameter("cpf");
        String matricula = request.getParameter("matricula");
        String login = request.getParameter("login");
        String atuacao1 = request.getParameter("atuacao1");
        String atuacao2 = request.getParameter("atuacao2");
        String atuacao3 = request.getParameter("atuacao3");
        ArrayList<String> areaAtuacao = new ArrayList<String>();
        areaAtuacao.add(atuacao1);
        areaAtuacao.add(atuacao2);
        areaAtuacao.add(atuacao3);
        Professor professor = new Professor(nome, cpf, endereco, telefone, matricula, login, senha, false);
        professor.setAreaAtuacao(areaAtuacao);
        professorService.salvarCadastro(professor);
        return new ModelAndView("redirect:/sucessoCadastroUser");
    }
}
