/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.trabalhoDAC.trabalhoDAC.controller;

import com.trabalhoDAC.trabalhoDAC.modelo.Defesa;
import com.trabalhoDAC.trabalhoDAC.modelo.Professor;
import com.trabalhoDAC.trabalhoDAC.modelo.Projeto;
import com.trabalhoDAC.trabalhoDAC.service.DefesaService;
import com.trabalhoDAC.trabalhoDAC.service.ProfessorService;
import com.trabalhoDAC.trabalhoDAC.service.ProjetoService;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
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
public class CadastroDefesaController {

    @Autowired
    private DefesaService defesaService;
    @Autowired
    private ProjetoService projetoService;
    @Autowired
    ProfessorService professorService;

    @GetMapping("cadastroDefesa")
    public ModelAndView serveCadastroDefesa() {
        ModelAndView modelAndView = new ModelAndView("cadastroDefesa");
        return modelAndView;
    }

    @PostMapping("/cadastroDefesa")
    public ModelAndView cadastroDefesa(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {

        response.setContentType("text/html;charset=UTF-8");
        String dataString = request.getParameter("data");
        String horaString = request.getParameter("hora");
        String sala = request.getParameter("sala");
        String titulo = request.getParameter("titulo");
        String nomeProfessor1 = request.getParameter("nomeProfessor1");
        String nomeProfessor2 = request.getParameter("nomeProfessor2");
        String nomeProfessor3 = request.getParameter("nomeProfessor3");
        
        Date data = new Date(dataString);
        Date hora = new Date(horaString);
        Projeto projeto = projetoService.buscarPorTitulo(titulo);
        List<Professor> convidados = new ArrayList<>();
        Professor professor = professorService.buscarPorNome(nomeProfessor1);
        convidados.add(professor);
        professor = professorService.buscarPorNome(nomeProfessor2);
        convidados.add(professor);
        professor = professorService.buscarPorNome(nomeProfessor3);
        convidados.add(professor);
        Defesa defesa = new Defesa(data, hora, convidados, sala, projeto, false);
        defesaService.salvar(defesa);
        return new ModelAndView("sucessoCadastroDefesa");

    }
}
