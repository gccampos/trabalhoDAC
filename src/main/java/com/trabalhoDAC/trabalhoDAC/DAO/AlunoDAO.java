/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.trabalhoDAC.trabalhoDAC.DAO;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.trabalhoDAC.trabalhoDAC.modelo.Aluno;
import com.trabalhoDAC.trabalhoDAC.modelo.Projeto;

/**
 *
 * @author guilherme
 */
@Repository
public interface AlunoDAO extends JpaRepository<Aluno, Long> {

	@Query("SELECT a FROM Aluno a where a.nome=?1")
	public Aluno buscarPorNome(String nome);

	@Query("SELECT a FROM Aluno a where a.matricula=?1")
	public Aluno buscarPorMatricula(String matricula);

	@Query("Select a FROM Aluno a where a.autorizado = false")
	public List<Aluno> listarNaoAutorizados();

	@Query("select a.projetoInscrito from Aluno a where a.ID=?1")
	public Projeto buscaPorAluno(Long idAluno);
}
