package br.com.starti.domain.entity;

import java.io.Serializable;
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
	@Column(name="id_empresa")
	private int idEmpresa;
	
	@NotBlank
	@Column(name="nome_fantasia")
	@Size(max=80)
	private String nomeFantasia;
	
	@NotBlank
	@Column(name="razao_social")
	@Size(max=45)
	private String razaoSocial;
	
	@NotBlank
	@Column
	@Size(max=45)
	private String segmento;
	
	@NotBlank
	@Column
	@Size(max=20)
	private String cnpj;
	
	@Embedded
	private Contato contato;
	
	@Embedded
	private Login login;
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name="endereco_id_endereco", referencedColumnName = "id_endereco")
	private Endereco endereco;
	
	@OneToMany(mappedBy="empresa")
	private Set<Vaga> vagas;
//	
//	@JoinColumn(name="id_permissao", table="permissao")
//	private int idPermissao;
}