package com.generation.projetoIntegrador.divulgueong.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.generation.projetoIntegrador.divulgueong.model.Tema;

@Repository
public interface TemaRepository extends JpaRepository <Tema, Long> {
	
	public List<Tema> findAllByOngContainingIgnoreCase(@Param("ong") String ong);
	
}
