package br.com.starti.domain.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import lombok.Data;

@Data
@Entity
@Table(name="empresa")
public class Empresa implements Serializable{

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@NotBlank
	@Column
	private int idEmpresa;
	
	@NotBlank
	@Column
	private String nomeFantasia;
	
	@NotBlank
	@Column
	private String cnpj;
	
	@NotBlank
	@Column
	private String razaoSocial;
	
	@NotBlank
	@Column
	private String segmento;
	
	@NotBlank
	@Size(max=25)
	private String telefone;
	
	@NotBlank
	@Email
	@Size(max=80)
	private String email;
	
	@Size(max=150)
	private String website;
	
	@OneToMany(mappedBy="endereco")
	private List<Endereco>endereco;
}