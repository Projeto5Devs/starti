package br.com.starti.domain.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import lombok.Data;

@Data
@Entity
@Table(name="candidato")
public class Candidato implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@NotBlank
	@Column(name="id_candidato")
	private int idCandidato;
	
	@NotBlank
	@Column
	@Size(max=20)
	private String nome;
	
	@NotBlank
	@Column
	@Size(max=60)
	private String sobrenome;
	
	@NotBlank
	@Column
	@Size(max=14)
	private String cpf;
	
	@NotBlank
	@Column(name="data_de_nascimento")
	private Date dataDeNascimento;
	
	@NotBlank
	@Column
	@Size(max=25)
	private String telefone;
	
	@NotBlank
	@Email
	@Column
	@Size(max=80)
	private String email;
	
	@Size(max=150)
	@Column
	private String website;
	
	@NotBlank
	@Column
	@Size(max=45)
	private String senha;
	
	@NotBlank
	@Column(name="ultima_vez_logado")
	@Size(max=45)
	private String ultimaVezLogado;
	
	@JoinColumn(name="id_endereco", table="endereco")
	private int idEndereco;
	
	@JoinColumn(name="id_permissao", table="permissao")
	private int idPermissao;
}
