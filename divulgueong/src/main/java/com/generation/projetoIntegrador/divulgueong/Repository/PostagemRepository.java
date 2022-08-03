package com.generation.projetoIntegrador.divulgueong.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import com.generation.projetoIntegrador.divulgueong.model.Postagem;

public interface PostagemRepository extends JpaRepository<Postagem, Long>{

	public List<Postagem> findAllByTituloContainingIgnoreCase(@Param("titulo")String titulo);
	
	public List<Postagem> findAllByAutorContainingIgnoreCase(@Param("autor")String autor);
}
