package br.com.starti.domain.entity;

import java.io.Serializable;
import java.util.Date;
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
	@Column(name = "id_pessoafisica")
	private Long idPessoaFisica;

	@NotNull
	@Column
	@Size(max = 20)
	private String nome;

	@NotNull
	@Column
	@Size(max = 60)
	private String sobrenome;

	@NotNull
	@Column
	@Size(max = 14)
	private String cpf;

	@NotNull
	@Past
	@Column(name = "data_de_nascimento")
	private Date dataDeNascimento;

	@Embedded
	private Contato contato;

	@NotNull
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "id_endereco", referencedColumnName = "id_endereco")
	private Endereco endereco;
	
	@NotNull
	@OneToOne(cascade=CascadeType.ALL)
	@JoinColumn(name="id_usuario", referencedColumnName = "id_usuario")
	private Usuario userId;

	@OneToMany(mappedBy = "pessoafisica")
	Set<Inscricao> inscricoes;

	

}
