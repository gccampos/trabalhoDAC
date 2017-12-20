/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.trabalhoDAC.trabalhoDAC.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.trabalhoDAC.trabalhoDAC.DAO.ProjetoDAO;
import com.trabalhoDAC.trabalhoDAC.modelo.Aluno;
import com.trabalhoDAC.trabalhoDAC.modelo.Projeto;

/**
 *
 * @author guilherme
 */
@Service
public class ProjetoService {

	@Autowired
	private ProjetoDAO projetoDAO;

	public Projeto buscarPorTitulo(String titulo) {
		return projetoDAO.buscaPorTitulo(titulo);
	}

	public Projeto buscarPorProfessor(Long idProfessor) {
		return projetoDAO.buscaPorOrientador(idProfessor);
	}

	public List<Projeto> listarProjetosEmAndamento() {
		return projetoDAO.listarProjetosEmAndamento();
	}

	public List<Projeto> listarProjetosEmAberto() {
		return projetoDAO.listarProjetosEmAberto();
	}

	public List<Projeto> listarProjetosConcluidos() {
		return projetoDAO.listarProjetosConcluidos();
	}

	public Projeto buscarPorId(Long id) {
		return projetoDAO.findOne(id);
	}

	public List<Projeto> listarTodos() {
		return projetoDAO.findAll();
	}

	public Projeto salvar(Projeto projeto) {
		return projetoDAO.save(projeto);
	}

	public List<Aluno> listarAlunosPorProjeto(Projeto projeto) {
		return projetoDAO.listarAlunosPorProjeto(projeto);
	}

}
