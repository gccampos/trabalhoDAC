/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.trabalhoDAC.trabalhoDAC.DAO;

import com.trabalhoDAC.trabalhoDAC.modelo.Professor;
import com.trabalhoDAC.trabalhoDAC.modelo.Projeto;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/**
 *
 * @author guilherme
 */
@Repository
public interface ProfessorDAO extends JpaRepository<Professor, Long> {

    @Query("SELECT p FROM Professor p where p.nome = ?1")
    public Professor buscarPorNome(String nome);

   
    @Query("SELECT p FROM Professor p where p.login = ?1")
    public Professor buscarPorLogin(String login);

    @Query("select p FROM Professor p where p.autorizado = false")
    public List<Professor> listarNaoAutorizados();

}
