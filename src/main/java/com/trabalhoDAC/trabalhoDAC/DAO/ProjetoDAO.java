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
public interface ProjetoDAO extends JpaRepository<Projeto, Long> {

	@Query("select p from Projeto p where p.titulo=?1")
	public Projeto buscaPorTitulo(String titulo);

	@Query("select p from Projeto p where p.orientador=?1")
	public Projeto buscaPorOrientador(Long professor);

	@Query(value = "select p.id, p.bibliografia, p.cronograma, p.nota, p.resumo, p.titulo, p.versao_final, p.disciplina_id, p.orientador_id from projeto p left join aluno a on (a.projeto_inscrito_id = p.id) where p.nota is null", nativeQuery = true)
	public List<Projeto> listarProjetosEmAndamento();

	@Query("select p from Projeto p where p.nota is not null")
	public List<Projeto> listarProjetosConcluidos();

	@Query(value = "select p.id, p.bibliografia, p.cronograma, p.nota, p.resumo, p.titulo, p.versao_final, p.disciplina_id, p.orientador_id from projeto p left join aluno a on (a.projeto_inscrito_id = p.id) where a.projeto_inscrito_id is null", nativeQuery = true)
	public List<Projeto> listarProjetosEmAberto();

	@Query(value = "select a.id, a.autorizado, a.matricula, a.nome, a.senha, a.cr, a.disciplina_id, a.projeto_inscrito_id from aluno a inner join projeto p on (a.projeto_inscrito_id = p.id) where p.id=?1", nativeQuery = true)
	public List<Aluno> listarAlunosPorProjeto(Projeto projeto);

}
