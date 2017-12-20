package com.trabalhoDAC.trabalhoDAC.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
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
			mav.addObject("alunoTcc", usr.getDisciplina().getTcc());
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
			mav.addObject("alunoTcc", usr.getDisciplina().getTcc());
			mav.addObject("userName", "Bem-vindo, " + usr.getNome() + "!");
		} else if ((prof = professorService.buscarPorMatricula((auth.getName()))) != null) {
			mav.addObject("roleProfessor", true);
			mav.addObject("profId", prof.getID());
			mav.addObject("roleAluno", false);
			mav.addObject("userName", "Bem-vindo, " + prof.getNome() + "!");
		} else {
			mav.addObject("roleAluno", false);
			mav.addObject("alunoTcc", 0);
		}
		List<Projeto> projetos = projetoService.listarProjetosEmAberto();
		Map<Projeto, String> m = new HashMap<>();
		for (Projeto p : projetos) {
			Professor o = professorService.buscarPorId(p.getOrientador().getID());
			if (o != null) {
				m.put(p, o.getNome());
			}
		}
		mav.addObject("projetos", projetos);
		mav.addObject("mapa", m);
		mav.setViewName("/projetos");
		return mav;
	}

	@GetMapping("/projetosinteresse")
	public ModelAndView mostrarProjetosInteresse() {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		Professor prof = null;
		ModelAndView mav = new ModelAndView("projetosInteresse");
		if ((prof = professorService.buscarPorMatricula((auth.getName()))) != null) {
			mav.addObject("roleProfessor", true);
			mav.addObject("profId", prof.getID());
			mav.addObject("roleAluno", false);
			mav.addObject("userName", "Bem-vindo, " + prof.getNome() + "!");
		} else {
			return new ModelAndView("403");
		}
		List<Projeto> projetos = projetoService.listarProjetosEmAberto();
		Map<Projeto, List<Aluno>> m = new HashMap<>();
		for (Projeto p : projetos) {
			List<Aluno> l = projetoService.listarAlunosPorProjeto(p);
			if (l != null) {
				m.put(p, l);
			}
		}
		mav.addObject("projetos", projetos);
		mav.addObject("mapa", m);
		return mav;

	}

	@PostMapping(value = "/projetosinteresse", params = { "alunoID", "projetoId" })
	public ModelAndView aceitarProjetosInteresse(HttpServletRequest request) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		Professor prof = null;
		ModelAndView mav = new ModelAndView("projetosInteresse");
		if ((prof = professorService.buscarPorMatricula((auth.getName()))) != null) {
			mav.addObject("roleProfessor", true);
			mav.addObject("profId", prof.getID());
			mav.addObject("roleAluno", false);
			mav.addObject("userName", "Bem-vindo, " + prof.getNome() + "!");
		} else {
			return new ModelAndView("403");
		}
		Projeto p = projetoService.buscarPorId(Long.parseLong(request.getParameter("projetoId")));
		Aluno a = alunoService.buscarPorId(Long.parseLong(request.getParameter("alunoID")));
		if ((a != null) && (p != null)) {
			a.setProjetoInscrito(p);
			a.setProjetosInteressado(new ArrayList<>());
		} else {
			return new ModelAndView("redirect:/projetosInteresse?error=true");
		}
		mav.setViewName("redirect:/projetosInteresse?sucesso=true");
		return mav;
	}

	@PostMapping(value = "/buscarProjetos", params = { "nomeProfessor", "situacao" })
	public ModelAndView buscarProjetosProfessor(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mav = new ModelAndView("home");
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		Aluno usr = null;
		Professor prof = null;
		if ((usr = alunoService.buscarPorMatricula((auth.getName()))) != null) {
			mav.addObject("roleProfessor", false);
			mav.addObject("roleAluno", true);
			mav.addObject("alunoTcc", usr.getDisciplina().getTcc());
			mav.addObject("userName", "Bem-vindo, " + usr.getNome() + "!");
		} else if ((prof = professorService.buscarPorMatricula((auth.getName()))) != null) {
			mav.addObject("roleProfessor", true);
			mav.addObject("roleAluno", false);
			mav.addObject("userName", "Bem-vindo, " + prof.getNome() + "!");
		} else {
			mav.addObject("roleAluno", false);
			mav.addObject("alunoTcc", 0);
		}
		String sit = request.getParameter("situacao");
		List<Projeto> projetos = null;
		switch (sit) {
		case "aberto":
			projetos = projetoService.listarProjetosEmAberto();
			break;
		case "andamento":
			projetos = projetoService.listarProjetosEmAndamento();
			break;
		case "concluido":
			projetos = projetoService.listarProjetosConcluidos();
			break;
		}
		Map<Projeto, String> m = new HashMap<>();
		List<Integer> l = new ArrayList<>();
		for (Projeto p : projetos) {
			Professor o = professorService.buscarPorId(p.getOrientador().getID());
			if (o.getNome().equalsIgnoreCase(request.getParameter("nomeProfessor"))) {
				m.put(p, o.getNome());
			} else {
				l.add(projetos.indexOf(p));
			}
		}
		for (Integer i : l) {
			projetos.remove(i.intValue());
		}
		mav.addObject("projetos", projetos);
		mav.addObject("mapa", m);
		mav.setViewName("/projetos");
		return mav;
	}

	@GetMapping("/cadastro")
	public ModelAndView cadastro() {
		ModelAndView mav = new ModelAndView("cadastro");
		return mav;
	}
}
