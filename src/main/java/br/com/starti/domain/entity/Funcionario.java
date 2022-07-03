package br.com.starti.domain.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import lombok.Data;

@Data
@Entity
@Table(name="funcionario")
public class Funcionario implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long idFuncionario;
	
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
	
	@Embedded
	private Contato contato;
	
	@Embedded
	private Login login;
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name="endereco_id_endereco", referencedColumnName = "id_endereco")
	private Endereco endereco;

}
