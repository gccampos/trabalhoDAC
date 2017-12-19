package com.trabalhoDAC.trabalhoDAC.controller;

import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import org.apache.commons.fileupload.FileUploadException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.trabalhoDAC.trabalhoDAC.modelo.Aluno;
import com.trabalhoDAC.trabalhoDAC.modelo.Disciplina;
import com.trabalhoDAC.trabalhoDAC.modelo.Professor;
import com.trabalhoDAC.trabalhoDAC.modelo.Projeto;
import com.trabalhoDAC.trabalhoDAC.service.AlunoService;
import com.trabalhoDAC.trabalhoDAC.service.DisciplinaService;
import com.trabalhoDAC.trabalhoDAC.service.ProfessorService;

@Controller
public class ImportaCSVController {

	@Autowired
	private AlunoService alunoService;

	@Autowired
	private ProfessorService professorService;

	@Autowired
	private DisciplinaService disciplinaService;

	@GetMapping("/importaCSV")
	public ModelAndView serveConsultaTodosTrabalhosConcluidos() {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		Professor prof = null;
		ModelAndView mav = new ModelAndView("cadastro");
		// if ((prof = professorService.buscarPorMatricula((auth.getName()))) != null) {
		// mav.addObject("userName", "Bem-vindo, " + prof.getNome() + "!");
		return mav;
		// } else
		// return new ModelAndView("403");
	}

	@PostMapping("/importaCSV")
	public ModelAndView importaCSVAluno(HttpServletRequest request, HttpServletResponse response,
			@RequestParam("csv") MultipartFile file)
			throws ServletException, IOException, SQLException, FileUploadException {
		response.setContentType("text/html;charset=UTF-8");
		boolean sucesso = false;
		if (request.getParameter("tipo").equalsIgnoreCase("aluno")) {
			sucesso = this.processaCSVAluno(file);
		} else if (request.getParameter("tipo").equalsIgnoreCase("professor")) {
			sucesso = this.processaCSVProfessor(file);
		} else {
			sucesso = false;
		}
		if (sucesso) {
			return new ModelAndView("redirect:/cadastro?sucesso=true");
		} else
			return new ModelAndView("redirect:/cadastro?error=true");
	}

	private boolean processaCSVProfessor(MultipartFile fi) {
		try {
			Iterable<CSVRecord> it = CSVFormat.EXCEL.withHeader().parse(new InputStreamReader(fi.getInputStream()));
			for (CSVRecord r : it) {
				String nome = r.get("nome");
				String senha = "root";
				String matricula = r.get("matricula");
				ArrayList<String> areaAtuacao = new ArrayList<String>();
				Professor p = professorService.buscarPorMatricula(matricula);
				if (p == null) {
					Professor professor = new Professor(nome, matricula, senha, false);
					professor.setAreaAtuacao(areaAtuacao);
					professorService.salvarCadastro(professor);
				}
			}
		} catch (Exception e) {
			return false;
		}
		return true;
	}

	private boolean processaCSVAluno(MultipartFile fi) {
		try {
			Iterable<CSVRecord> it = CSVFormat.EXCEL.withHeader().parse(new InputStreamReader(fi.getInputStream()));
			for (CSVRecord r : it) {
				String nome = r.get("nome");
				String senha = "root";
				String matricula = r.get("matricula");
				String CR = r.get("CR");
				String disc = r.get("tcc");
				Aluno a = alunoService.buscarPorMatricula(matricula);
				if (a == null) {
					List<Projeto> interesses = new ArrayList<>();
					Disciplina d = disciplinaService.buscarPorId(Long.parseLong(disc));
					Aluno aluno = new Aluno(nome, matricula, senha, false);
					aluno.setProjetosInteressado(interesses);
					aluno.setCR(Double.parseDouble(CR));
					aluno.setDisciplina(d);
					alunoService.salvarCadastro(aluno, Integer.parseInt(disc));
				}
			}
		} catch (Exception e) {
			return false;
		}
		return true;
	}
}
