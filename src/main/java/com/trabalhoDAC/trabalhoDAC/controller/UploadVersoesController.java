package com.trabalhoDAC.trabalhoDAC.controller;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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

import com.trabalhoDAC.trabalhoDAC.modelo.Projeto;
import com.trabalhoDAC.trabalhoDAC.service.ProjetoService;

@Controller
public class UploadVersoesController {
	@Autowired
	private ProjetoService projetoService;

	@GetMapping("/uploadVersoes")
	public ModelAndView serveConsultaTodosTrabalhosConcluidos() {
		ModelAndView modelAndView = new ModelAndView("uploadVersoes");
		return modelAndView;
	}

	@PostMapping("/uploadVersao")
	public ModelAndView uploadVersaoParcial(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException, SQLException, FileUploadException {
		response.setContentType("text/html;charset=UTF-8");
		DiskFileItemFactory factory = new DiskFileItemFactory();
		ServletFileUpload upload = new ServletFileUpload(factory);
		boolean sucesso = false;
		String tipo = "";
		String projeto = "";
		List<FileItem> items = upload.parseRequest(new ServletRequestContext(request));
		Iterator<FileItem> iter = items.iterator();
		while (iter.hasNext()) {
			FileItem item = iter.next();

			if (item.isFormField()) {
				if (item.getName().equalsIgnoreCase("tipo")) {
					tipo = item.getString();
				} else {
					projeto = item.getString();
				}
			} else {
				if (tipo.equalsIgnoreCase("parcial")) {
					sucesso = this.adicionaVersaoParcial(projeto, item);
				} else if (tipo.equalsIgnoreCase("final")) {
					sucesso = this.adicionaVersaoFinal(projeto, item);
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

	private boolean adicionaVersaoFinal(String projeto, FileItem item) {
		try {
			Projeto pj = projetoService.buscarPorTitulo(projeto);
			File temp = new File("/home/Salle/ArquivosDAC");
			item.write(temp);
			pj.setVersaoFinal(temp);
			temp.delete();
			projetoService.salvar(pj);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	private boolean adicionaVersaoParcial(String projeto, FileItem item) {
		try {
			Projeto pj = projetoService.buscarPorTitulo(projeto);
			File temp = new File("/home/Salle/ArquivosDAC");
			item.write(temp);
			pj.getVersoesParciais().add(temp);
			temp.delete();
			projetoService.salvar(pj);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

}
