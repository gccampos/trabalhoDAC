/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.trabalhoDAC.trabalhoDAC.controller;

import com.trabalhoDAC.trabalhoDAC.modelo.Professor;
import com.trabalhoDAC.trabalhoDAC.modelo.Projeto;
import com.trabalhoDAC.trabalhoDAC.service.ProfessorService;
import com.trabalhoDAC.trabalhoDAC.service.ProjetoService;
import java.io.IOException;
import java.sql.SQLException;
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
public class ConsultaAtividadesProfessorController {

    @Autowired
    private ProfessorService professorService;
    @Autowired
    private ProjetoService projetoService;

    @GetMapping("consultaAtividadesProfessor")
    public ModelAndView serveConsultaAtividadesProfessor() {
        ModelAndView modelAndView = new ModelAndView("consultaAtividadesProfessor");
        return modelAndView;
    }

    @PostMapping("/consultaAtividadesProfessor")
    public ModelAndView consultaAtividadesProfessor(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {
        response.setContentType("text/html;charset=UTF-8");
        String nomeProfessor = request.getParameter("nomeProfessor");
        Professor professor = professorService.buscarPorNome(nomeProfessor);
        Projeto projeto = projetoService.buscarPorProfessor(professor.getID());
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("projeto", projeto);
        return modelAndView;
    }
}
