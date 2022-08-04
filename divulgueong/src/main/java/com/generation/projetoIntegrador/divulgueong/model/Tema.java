package com.generation.projetoIntegrador.divulgueong.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table (name = "tb_temas")
public class Tema {

 @Id
 @GeneratedValue(strategy = GenerationType.IDENTITY)
 private Long id;
 
 @NotBlank (message = "preenchimento obrigatório")
 @Size(min = 5, max = 255)
 private String ong;
 
 @NotBlank (message = "preenchimento obrigatório")
 @Size(min = 5, max = 255)
 private String governo;

 @OneToMany(mappedBy = "tema", cascade = CascadeType.ALL)
 @JsonIgnoreProperties("tema")
 private List<Postagem> postagem;

public Long getId() {
	return id;
}

public void setId(Long id) {
	this.id = id;
}

public String getOng() {
	return ong;
}

public void setOng(String ong) {
	this.ong = ong;
}

public String getGoverno() {
	return governo;
}

public void setGoverno(String governo) {
	this.governo = governo;
}

public List<Postagem> getPostagem() {
	return postagem;
}

public void setPostagem(List<Postagem> postagem) {
	this.postagem = postagem;
}
 

}
