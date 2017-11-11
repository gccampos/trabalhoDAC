/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.trabalhoDAC.trabalhoDAC.DAO;

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
public interface ProjetoDAO extends JpaRepository<Projeto, Long> {

    @Query("select p from Projeto p where p.titulo=?1")
    public Projeto buscaPorTitulo(String titulo);

    @Query("select p from Projeto p where p.id_orientador=?1")
    public Projeto buscaPorOrientador(Long professor);

    @Query("select p from Projeto p where p.nota is null")
    public List<Projeto> listarProjetosEmAndamento();

    @Query("select p from Projeto p where p.nota is not null")
    public List<Projeto> listarProjetosConcluidos();
}
