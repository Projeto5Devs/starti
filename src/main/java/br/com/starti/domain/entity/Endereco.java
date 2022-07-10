package br.com.starti.domain.entity;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnore;


import lombok.Data;

@Data
@Entity
@Table(name="endereco")
public class Endereco implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id_endereco")
	private Long idEndereco;
	
	@NotBlank
	@Column
	@Size(max=100)
	private String rua;
	
	@NotBlank
	@Column
	@Size(max=10)
	private String numero;
	
	@Column
	@Size(max=30)
	private String complemento;
	
	@NotBlank
	@Column
	@Size(max=50)
	private String bairro;
	
	@NotBlank
	@Column
	@Size(max=10)
	private String cep;
	
	@NotBlank
	@Column
	@Size(max=50)
	private String cidade;
	
	@NotBlank
	@Column
	@Size(max=2)
	private String uf;
	
	@OneToOne(mappedBy = "endereco")
	@JsonIgnore
	private PessoaFisica pessoaFisica;
	
	@OneToOne(mappedBy="endereco", cascade = CascadeType.ALL)
	@JsonIgnore
	private Empresa empresa;

}
