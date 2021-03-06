package br.com.starti.domain.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;

import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;

import lombok.Data;

@Data
@Entity
@Table(name = "pessoa_fisica")
public class PessoaFisica implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id_pessoa_fisica")
	private Long idPessoaFisica;

	@NotBlank
	@Column
	@Size(max = 20)
	private String nome;

	@NotBlank
	@Column
	@Size(max = 60)
	private String sobrenome;

	@NotBlank
	@Column
	@Size(max = 14)
	private String cpf;

	@NotNull
	@Past
	@Column(name = "data_de_nascimento")
	private Date dataDeNascimento;

	@Embedded
	private Contato contato;


	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "endereco_id_endereco", referencedColumnName = "id_endereco")
	private Endereco endereco;
	
	@OneToOne(cascade=CascadeType.ALL)
	@JoinColumn(name="usuario_id_usuario", referencedColumnName = "id_usuario")
	private Usuario userId;

	@OneToMany(mappedBy = "pessoafisica")
	Set<Inscricao> inscricoes;

	

}
