/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.trabalhoDAC.trabalhoDAC.controller;

import com.trabalhoDAC.trabalhoDAC.modelo.Disciplina;
import com.trabalhoDAC.trabalhoDAC.service.DisciplinaService;
import java.io.IOException;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author guilherme
 */
@Controller
public class CadastroDisciplinaController {

    private DisciplinaService disciplinaService;

    @GetMapping("cadastroDisciplina")
    public ModelAndView serveCadastroDisciplina() {
        ModelAndView modelAndView = new ModelAndView("cadastroDisciplina");
        return modelAndView;
    }

    @PostMapping("/cadastroDisciplina")
    public ModelAndView cadastroDisciplina(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {
        response.setContentType("text/html;charset=UTF-8");
        String nome = request.getParameter("nome");
        String tccString = request.getParameter("tcc");
        int tcc = Integer.parseInt(tccString);
        Disciplina disciplina = new Disciplina(nome, tcc);
        disciplinaService.salvar(disciplina);
        return new ModelAndView("sucessoCadastroDisciplina");
    }

}
