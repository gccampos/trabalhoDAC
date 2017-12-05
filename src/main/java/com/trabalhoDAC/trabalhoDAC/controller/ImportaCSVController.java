package com.trabalhoDAC.trabalhoDAC.controller;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import org.apache.tomcat.util.http.fileupload.FileItem;
import org.apache.tomcat.util.http.fileupload.FileUploadException;
import org.apache.tomcat.util.http.fileupload.disk.DiskFileItemFactory;
import org.apache.tomcat.util.http.fileupload.servlet.ServletFileUpload;
import org.apache.tomcat.util.http.fileupload.servlet.ServletRequestContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.trabalhoDAC.trabalhoDAC.modelo.Aluno;
import com.trabalhoDAC.trabalhoDAC.modelo.Professor;
import com.trabalhoDAC.trabalhoDAC.modelo.Projeto;
import com.trabalhoDAC.trabalhoDAC.service.AlunoService;
import com.trabalhoDAC.trabalhoDAC.service.ProfessorService;

@Controller
public class ImportaCSVController {

	@Autowired
	private AlunoService alunoService;

	@Autowired
	private ProfessorService professorService;

	@GetMapping("/importaCSV")
	public ModelAndView serveConsultaTodosTrabalhosConcluidos() {
		ModelAndView modelAndView = new ModelAndView("importaCSV");
		return modelAndView;
	}

	@PostMapping("/importaCSV")
	public ModelAndView importaCSVAluno(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException, SQLException, FileUploadException {
		response.setContentType("text/html;charset=UTF-8");
		DiskFileItemFactory factory = new DiskFileItemFactory();
		ServletFileUpload upload = new ServletFileUpload(factory);
		boolean sucesso = false;
		String tipo = "";
		List<FileItem> items = upload.parseRequest(new ServletRequestContext(request));
		Iterator<FileItem> iter = items.iterator();
		while (iter.hasNext()) {
			FileItem item = iter.next();

			if (item.isFormField()) {
				tipo = item.getString();
			} else {
				if (tipo.equalsIgnoreCase("aluno")) {
					sucesso = this.processaCSVAluno(item);
				} else if (tipo.equalsIgnoreCase("professor")) {
					sucesso = this.processaCSVProfessor(item);
				} else {
					sucesso = false;
				}
			}
		}
		if (sucesso) {
			return new ModelAndView("redirect:/sucessoImport");
		} else
			return new ModelAndView("redirect:/erroImport");
	}

	private boolean processaCSVProfessor(FileItem fi) {
		File csv = new File("/home/Salle/ArquivosDAC");
		try {
			fi.write(csv);
			Reader in = new FileReader(csv);
			Iterable<CSVRecord> it = CSVFormat.EXCEL.parse(in);
			for (CSVRecord r : it) {

				String nome = r.get("nome");
				String endereco = r.get("endereco");
				String telefone = r.get("telefone");
				String senha = r.get("senha");
				String cpf = r.get("cpf");
				String matricula = r.get("matricula");
				String login = r.get("login");
				ArrayList<String> areaAtuacao = new ArrayList<String>();
				Professor professor = new Professor(nome, cpf, endereco, telefone, matricula, login, senha, false);
				professor.setAreaAtuacao(areaAtuacao);
				professorService.salvarCadastro(professor);
				return true;
			}
		} catch (Exception e) {
			return false;
		}
		return false;
	}

	private boolean processaCSVAluno(FileItem fi) {
		File csv = new File("/home/Salle/ArquivosDAC");
		try {
			fi.write(csv);
			Reader in = new FileReader(csv);
			Iterable<CSVRecord> it = CSVFormat.EXCEL.parse(in);
			for (CSVRecord r : it) {
				String nome = r.get("nome");
				String endereco = r.get("endereco");
				String telefone = r.get("telefone");
				String senha = r.get("senha");
				String cpf = r.get("cpf");
				String matricula = r.get("matricula");
				String login = r.get("login");
				List<Projeto> interesses = new ArrayList<>();
				Aluno aluno = new Aluno(nome, cpf, endereco, telefone, matricula, login, senha, false);
				aluno.setProjetosInteressado(interesses);
				alunoService.salvarCadastro(aluno);
				return true;
			}
		} catch (Exception e) {
			return false;
		}
		return false;
	}
}
