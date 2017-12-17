package com.trabalhoDAC.trabalhoDAC.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import com.trabalhoDAC.trabalhoDAC.modelo.Aluno;
import com.trabalhoDAC.trabalhoDAC.modelo.Professor;
import com.trabalhoDAC.trabalhoDAC.modelo.Projeto;
import com.trabalhoDAC.trabalhoDAC.service.AlunoService;
import com.trabalhoDAC.trabalhoDAC.service.ProfessorService;
import com.trabalhoDAC.trabalhoDAC.service.ProjetoService;

@Controller
public class ServePaginasController {

	@Autowired
	private AlunoService alunoService;
	@Autowired
	private ProfessorService professorService;
	@Autowired
	private ProjetoService projetoService;

	@GetMapping("/index")
	public ModelAndView index() {
		ModelAndView mav = new ModelAndView("index");
		return mav;
	}

	@GetMapping("/home")
	public ModelAndView home() {
		ModelAndView mav = new ModelAndView("home");
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		Aluno usr = null;
		Professor prof = null;
		if ((usr = alunoService.buscarPorMatricula((auth.getName()))) != null) {
			mav.addObject("roleProfessor", false);
			mav.addObject("roleAluno", true);
			mav.addObject("userName", "Bem-vindo, " + usr.getNome() + "!");
		} else if ((prof = professorService.buscarPorMatricula((auth.getName()))) != null) {
			mav.addObject("roleProfessor", true);
			mav.addObject("roleAluno", false);
			mav.addObject("userName", "Bem-vindo, " + prof.getNome() + "!");
		}
		mav.setViewName("/home");
		return mav;
	}

	@GetMapping("/buscarProjetos")
	public ModelAndView buscarProjetos() {
		ModelAndView mav = new ModelAndView("home");
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		Aluno usr = null;
		Professor prof = null;
		if ((usr = alunoService.buscarPorMatricula((auth.getName()))) != null) {
			mav.addObject("roleProfessor", false);
			mav.addObject("roleAluno", true);
			mav.addObject("userName", "Bem-vindo, " + usr.getNome() + "!");
		} else if ((prof = professorService.buscarPorMatricula((auth.getName()))) != null) {
			mav.addObject("roleProfessor", true);
			mav.addObject("roleAluno", false);
			mav.addObject("userName", "Bem-vindo, " + prof.getNome() + "!");
		} else {
			mav.addObject("roleAluno", false);
		}
		List<Projeto> projetos = projetoService.listarProjetosEmAberto();
		mav.addObject("projetos", projetos);
		mav.setViewName("/projetos");
		return mav;
	}

	@GetMapping("/cadastro")
	public ModelAndView cadastro() {
		ModelAndView mav = new ModelAndView("cadastro");
		return mav;
	}
}
