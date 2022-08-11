package com.generation.projetoIntegrador.divulgueong.Service;

import java.nio.charset.Charset;
import java.util.Optional;

import org.apache.commons.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.generation.projetoIntegrador.divulgueong.Repository.UsuarioRepository;
import com.generation.projetoIntegrador.divulgueong.model.Usuario;
import com.generation.projetoIntegrador.divulgueong.model.UsuarioLogin;

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
			usuario.setSenha(criptografarSenha(usuario.getSenha()));
			return Optional.of(usuarioRepository.save(usuario));
			
		}
		throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuario não encontrado!" , null);
	}
	
	public Optional<UsuarioLogin> logarUsuario(Optional<UsuarioLogin> usuarioLogin) {
	
		Optional<Usuario> usuario = usuarioRepository
				.findByUsuario(usuarioLogin.get().getUsuario());

		
		if (usuario.isPresent()) {

		
			if (compararSenhas(usuarioLogin.get().getSenha(),
					usuario.get().getSenha())) {

			
				usuarioLogin.get().setId(usuario.get().getId());
				usuarioLogin.get().setNome(usuario.get().getNome());
				usuarioLogin.get().setToken(gerarBasicToken(usuarioLogin.get().getUsuario(), usuarioLogin.get().getSenha()));
				usuarioLogin.get().setSenha(usuario.get().getSenha());
				
				

				
				return usuarioLogin;

			}
		}
		throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Usuário ou senha Invalidos", null);
	}
	
	private String criptografarSenha(String senha) {

		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		String SenhaEncorder = encoder.encode(senha);
		
		
		
		return SenhaEncorder ;

	}
	

	private boolean compararSenhas(String senhaDigitada, String senhaBanco) {
		
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		
		return encoder.matches(senhaDigitada, senhaBanco);

	}
	private String gerarBasicToken(String usuario, String senha) {

		String token = usuario + ":" + senha;
		byte[] tokenBase64 = Base64.encodeBase64(token.getBytes(Charset.forName("US-ASCII")));
		return "Basic " + new String(tokenBase64);

	}
}
