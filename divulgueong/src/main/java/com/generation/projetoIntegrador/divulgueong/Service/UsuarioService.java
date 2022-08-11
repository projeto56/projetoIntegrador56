package com.generation.projetoIntegrador.divulgueong.Service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.generation.projetoIntegrador.divulgueong.Repository.UsuarioRepository;
import com.generation.projetoIntegrador.divulgueong.model.Usuario;

@Service
public class UsuarioService {
	
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	public Optional<Usuario> cadastrarUsuario(Usuario usuario){
		
		if(usuarioRepository.findByUsuario(usuario.getUsuario()).isPresent())
			
			return Optional.empty();
		
		usuario.setSenha(criptografarSenha(usuario.getSenha()));
		
		return Optional.of(usuarioRepository.save(usuario));
		
		
	}

	
	public Optional<Usuario> atualizarUsuario(Usuario usuario) {
		
		if(usuarioRepository.findById(usuario.getId()).isPresent()) {
			
			Optional<Usuario> buscaUsuario = usuarioRepository
					.findByUsuario(usuario.getUsuario());
			
			if (buscaUsuario.isPresent()) {
				
				if(buscaUsuario.get().getId() != usuario.getId());
				throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
						"Usuario Já Existe!!", null);
				
			}
			
		}
		
	}
	
	

}
