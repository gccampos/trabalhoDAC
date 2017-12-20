package com.trabalhoDAC.trabalhoDAC.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.trabalhoDAC.trabalhoDAC.modelo.Aluno;
import com.trabalhoDAC.trabalhoDAC.modelo.Projeto;
import com.trabalhoDAC.trabalhoDAC.service.AlunoService;
import com.trabalhoDAC.trabalhoDAC.service.ProjetoService;

@Controller
public class InteresseController {

	@Autowired
	private ProjetoService projetoService;

	@Autowired
	private AlunoService alunoService;

	@PostMapping(value = "/interesseProjeto", params = "projetoId")
	public ModelAndView serveConsultaTodosTrabalhosConcluidos(HttpServletRequest request) {
		ModelAndView mav = new ModelAndView("redirect:/projetos?sucesso=true");
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		Aluno usr = null;
		if ((usr = alunoService.buscarPorMatricula((auth.getName()))) != null) {
			mav.addObject("roleProfessor", false);
			mav.addObject("roleAluno", true);
			mav.addObject("alunoTcc", usr.getDisciplina().getTcc());
			mav.addObject("userName", "Bem-vindo, " + usr.getNome() + "!");
			Projeto p = projetoService.buscarPorId(Long.parseLong(request.getParameter("projetoId")));
			if (usr.getProjetosInteressado().contains(p)) {
				return new ModelAndView("redirect:/projetos?error=true");
			}
			usr.getProjetosInteressado().add(p);
			alunoService.salvar(usr);
			return mav;
		} else {
			return new ModelAndView("redirect:/projetos?error=true");
		}
	}

}
