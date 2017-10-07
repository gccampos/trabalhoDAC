/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.trabalhoDAC.trabalhoDAC.DAO;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/**
 *
 * @author guilherme
 */
@Repository
public interface Aluno extends JpaRepository<Aluno, Long> {

    @Query("SELECT a FROM Aluno a where a.nome=?1")
    public Aluno buscarPorNome(String nome);
    
    @Query("SELECT a FROM Aluno a where a.login=?1")
    public Aluno buscarPorLogin(String login);
    
    
}
