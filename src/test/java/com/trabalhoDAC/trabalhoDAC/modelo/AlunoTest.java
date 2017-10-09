/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.trabalhoDAC.trabalhoDAC.modelo;

import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author guilherme
 */
public class AlunoTest {
    
    public AlunoTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of getDisciplina method, of class Aluno.
     */
    @Test
    public void testGetDisciplina() {
        System.out.println("getDisciplina");
        Aluno instance = null;
        Disciplina expResult = null;
        Disciplina result = instance.getDisciplina();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setDisciplina method, of class Aluno.
     */
    @Test
    public void testSetDisciplina() {
        System.out.println("setDisciplina");
        Disciplina disciplina = null;
        Aluno instance = null;
        instance.setDisciplina(disciplina);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getProjeto method, of class Aluno.
     */
    @Test
    public void testGetProjeto() {
        System.out.println("getProjeto");
        Aluno instance = null;
        Projeto expResult = null;
        Projeto result = instance.getProjeto();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setProjeto method, of class Aluno.
     */
    @Test
    public void testSetProjeto() {
        System.out.println("setProjeto");
        Projeto projeto = null;
        Aluno instance = null;
        instance.setProjeto(projeto);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getInteresses method, of class Aluno.
     */
    @Test
    public void testGetInteresses() {
        System.out.println("getInteresses");
        Aluno instance = null;
        List<String> expResult = null;
        List<String> result = instance.getInteresses();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setInteresses method, of class Aluno.
     */
    @Test
    public void testSetInteresses() {
        System.out.println("setInteresses");
        List<String> interesses = null;
        Aluno instance = null;
        instance.setInteresses(interesses);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
