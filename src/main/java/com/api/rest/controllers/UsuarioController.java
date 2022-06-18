package com.api.rest.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.api.rest.entities.Usuario;
import com.api.rest.exception.RecursoNaoEncontradoException;
import com.api.rest.repositories.UsuarioRepository;


@RestController
@RequestMapping("/usuario")
public class UsuarioController {
	
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@GetMapping()
	public List<Usuario> findAll() {
		List<Usuario>usuarioList = usuarioRepository.findAll();
		return usuarioList;
	}
	
	@PostMapping()
	@ResponseStatus(HttpStatus.CREATED)
	public Usuario create(@RequestBody Usuario usuario) {
		Usuario usuarioSave = usuarioRepository.save(usuario);
		return usuarioSave;
	}
	
	@PutMapping("/{id}")
	public Usuario update(@PathVariable Long id, @RequestBody Usuario usuario) {
		Usuario usuarioUpdate = usuarioRepository.findById(id)
				.orElseThrow(()-> new EmptyResultDataAccessException(1));
		BeanUtils.copyProperties(usuario, usuarioUpdate, "id");
		return usuarioRepository.save(usuarioUpdate);
	}
	
	@DeleteMapping("/{id}")
	public void delete(@PathVariable Long id) {
		usuarioRepository.deleteById(id);
	}
	
	 @GetMapping("/{id}")
	    public Usuario buscar(@PathVariable Long id) {
		 Optional<Usuario> resultado = usuarioRepository.findById(id);
		 if(resultado.isEmpty()) {
			 throw new RecursoNaoEncontradoException();
		 }
		 Usuario usuario = resultado.get();
		 return usuario;
	    }

}
