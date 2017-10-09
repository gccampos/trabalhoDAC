/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.trabalhoDAC.trabalhoDAC.service;

/**
 *
 * @author guilherme
 */
import com.trabalhoDAC.trabalhoDAC.DAO.PapelDAO;
import com.trabalhoDAC.trabalhoDAC.modelo.Papel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Salle
 */
@Service
public class PapelService {

    @Autowired
    private PapelDAO papelDAO;

    public Papel buscarPorPapel(String papel) {
        return papelDAO.buscarPorPapel(papel);
    }

}
