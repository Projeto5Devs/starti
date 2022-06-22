package br.com.starti.domain.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import lombok.Data;

@Data
@Entity
@Table(name="endereco")
public class Endereco implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer idEndereco;
	
	@NotBlank
	@Size(max=100)
	private String rua;
	
	@NotBlank
	@Size(max=10)
	private String numero;
	
	@NotBlank
	@Size(max=50)
	private String bairro;
	
	@NotBlank
	@Size(max=10)
	private String cep;
	
	@NotBlank
	@Size(max=50)
	private String cidade;
	
	@NotBlank
	@Size(max=2)
	private String uf;
}