/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.trabalhoDAC.trabalhoDAC.DAO;

import com.trabalhoDAC.trabalhoDAC.modelo.Disciplina;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/**
 *
 * @author guilherme
 */
@Repository
public interface DisciplinaDAO extends JpaRepository<Disciplina, Long> {

    @Query("SELECT d FROM Disciplina d where d.nome =?1")
    public Disciplina buscarPorNome(String nome);

}
