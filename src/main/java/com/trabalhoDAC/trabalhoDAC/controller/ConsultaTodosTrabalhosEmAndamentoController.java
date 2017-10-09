/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.trabalhoDAC.trabalhoDAC.controller;

import com.trabalhoDAC.trabalhoDAC.modelo.Projeto;
import com.trabalhoDAC.trabalhoDAC.service.ProjetoService;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
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
public class ConsultaTodosTrabalhosEmAndamentoController {

    @Autowired
    private ProjetoService projetoService;

    @GetMapping("/consultaTodosTrabalhosEmAndamento")
    public ModelAndView serveConsultaTodosTrabalhosEmAndamento() {
        ModelAndView modelAndView = new ModelAndView("consultaTodosTrabalhosEmAndamento");
        return modelAndView;
    }

    @PostMapping("/consultaTodosTrabalhosEmAndamento")
    public ModelAndView consultaTodosTrabalhosEmAndamento(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {
        response.setContentType("text/html;charset=UTF-8");
        ModelAndView modelAndView = new ModelAndView();
        ArrayList<Projeto> projetos = (ArrayList<Projeto>) projetoService.listarProjetosEmAndamento();
        modelAndView.addObject("projetos", projetos);
        return modelAndView;
    }
}
