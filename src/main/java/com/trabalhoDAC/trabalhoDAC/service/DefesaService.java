/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.trabalhoDAC.trabalhoDAC.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.trabalhoDAC.trabalhoDAC.DAO.DefesaDAO;
import com.trabalhoDAC.trabalhoDAC.modelo.Defesa;

/**
 *
 * @author guilherme
 */
@Service
public class DefesaService {

	@Autowired
	private DefesaDAO defesaDAO;

	// public Defesa buscarPorOrientador(Long id, Professor orientador) {
	// return defesaDAO.buscarPorOrientador(id, orientador);
	// }

	public Defesa buscarPorData(Date data) {
		return defesaDAO.buscarPorData(data);
	}

	public Defesa buscarPorId(Long id) {
		return defesaDAO.findOne(id);
	}

	public List<Defesa> listarTodos() {
		return defesaDAO.findAll();
	}

	public Defesa salvar(Defesa defesa) {
		return defesaDAO.save(defesa);
	}
}
