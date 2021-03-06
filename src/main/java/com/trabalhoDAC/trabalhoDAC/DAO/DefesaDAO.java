/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.trabalhoDAC.trabalhoDAC.DAO;

import java.util.Date;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.trabalhoDAC.trabalhoDAC.modelo.Defesa;

/**
 *
 * @author guilherme
 */
@Repository
public interface DefesaDAO extends JpaRepository<Defesa, Long> {

	// @Query("select d from Defesa d where d.ID = ?1 and d.orientador = ?2")
	// public Defesa buscarPorOrientador(Long id, Professor orientador);

	@Query("select d from Defesa d where d.data=?1")
	public Defesa buscarPorData(Date data);
}
