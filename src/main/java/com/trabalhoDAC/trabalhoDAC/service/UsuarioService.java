package com.trabalhoDAC.trabalhoDAC.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.trabalhoDAC.trabalhoDAC.DAO.UsuarioDAO;
import com.trabalhoDAC.trabalhoDAC.modelo.Usuario;

@Service
public class UsuarioService {
	@Autowired
	private UsuarioDAO usuarioDAO;
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;

	public Usuario buscarPorId(Long id) {
		return usuarioDAO.findOne(id);
	}

	public Usuario buscarPorMatricula(String matricula) {
		return usuarioDAO.buscarPorMatricula(matricula);
	}

	public List<Usuario> listarTodos() {
		return usuarioDAO.findAll();
	}

	public void salvar(Usuario usuario) {
		usuarioDAO.save(usuario);
	}

	public Usuario salvarCadastro(Usuario usuario) {
		usuario.setSenha(bCryptPasswordEncoder.encode(usuario.getSenha()));
		usuarioDAO.saveAndFlush(usuario);
		return usuario;
	}
}
