/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.trabalhoDAC.trabalhoDAC.service;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.trabalhoDAC.trabalhoDAC.DAO.AlunoDAO;
import com.trabalhoDAC.trabalhoDAC.modelo.Aluno;
import com.trabalhoDAC.trabalhoDAC.modelo.Papel;
import com.trabalhoDAC.trabalhoDAC.modelo.Projeto;

/**
 *
 * @author guilherme
 */
@Service
public class AlunoService {

	@Autowired
	private AlunoDAO alunoDAO;
	@Autowired
	private PapelService papelService;
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;

	public Aluno buscarPorNome(String nome) {
		return alunoDAO.buscarPorNome(nome);
	}

	public Projeto buscarPorAluno(Long idALuno) {
		return alunoDAO.buscaPorAluno(idALuno);
	}

	public Aluno buscarPorId(Long id) {
		return (Aluno) alunoDAO.findOne(id);
	}

	public List<Aluno> listarTodos() {
		return alunoDAO.findAll();
	}

	public Aluno buscarPorMatricula(String matricula) {
		return alunoDAO.buscarPorMatricula(matricula);
	}

	public List<Aluno> listarNaoAutorizados() {
		return alunoDAO.listarNaoAutorizados();
	}

	public Aluno salvarCadastro(Aluno aluno, int tcc) {
		aluno.setSenha(bCryptPasswordEncoder.encode(aluno.getSenha()));
		String nomePapel = "TCC" + tcc;
		Papel papel = papelService.buscarPorPapel(nomePapel);
		aluno.setPapel(new HashSet<Papel>(Arrays.asList(papel)));
		alunoDAO.saveAndFlush(aluno);
		return aluno;
	}

	public void salvar(Aluno aluno) {
		alunoDAO.save(aluno);
	}
}
