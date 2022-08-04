package com.generation.projetoIntegrador.divulgueong.Controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.generation.projetoIntegrador.divulgueong.Repository.UsuarioRepository;
import com.generation.projetoIntegrador.divulgueong.model.Usuario;

@RestController
@RequestMapping("/usuario")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class UsuarioController {

	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@GetMapping
	public ResponseEntity<List<Usuario>> getAll(){
		return ResponseEntity.ok(usuarioRepository.findAll());
		
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Usuario> getByIt(@PathVariable Long id){
		return usuarioRepository.findById(id).map(resposta -> ResponseEntity.ok(resposta))
				.orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
	}
	
	@GetMapping("/nome/{nome}")
	public ResponseEntity<List<Usuario>> getByNome(@PathVariable String nome){
		return ResponseEntity.ok(usuarioRepository.findAllByNomeContainingIgnoreCase(nome));
	}
	
	@PutMapping
	public ResponseEntity<Usuario> put(@Valid @RequestBody Usuario usuario){
		return usuarioRepository.findById(usuario.getId()).map(resposta -> ResponseEntity.status(HttpStatus.CREATED)
				.body(usuarioRepository.save(usuario))).orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());	
	}
	
	
	@ResponseStatus(HttpStatus.NO_CONTENT)
	@DeleteMapping("/{id}")
	public void delete(@PathVariable Long id) {
		Optional<Usuario> usuario = usuarioRepository.findById(id);
		
		if(usuario.isEmpty())
			throw new ResponseStatusException(HttpStatus.NOT_FOUND);
		
		usuarioRepository.deleteById(id);
	}
	
	
	
}

