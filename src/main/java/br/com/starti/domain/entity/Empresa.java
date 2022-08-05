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
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import com.fasterxml.jackson.annotation.JsonIgnore;


import lombok.Data;

@Data
@Entity
@Table(name="empresa")
public class Empresa implements Serializable{

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="id_empresa")
	private Long idEmpresa;
	
	@NotNull
	@Column(name="nome_fantasia")
	@Size(max=80)
	private String nomeFantasia;
	
	@NotNull
	@Column(name="razao_social")
	@Size(max=45)
	private String razaoSocial;
	
	@NotNull
	@Column
	@Size(max=45)
	private String segmento;
	
	@NotNull
	@Column
	@Size(max=20)
	private String cnpj;
	
	@Embedded
	private Contato contato;
	
	@NotNull
	@OneToOne(cascade=CascadeType.ALL)
	@JoinColumn(name="id_usuario", referencedColumnName = "id_usuario")
	private Usuario userId;
	
	@NotNull
	@OneToOne(cascade=CascadeType.ALL)
	@JoinColumn(name="id_endereco", referencedColumnName = "id_endereco")
	private Endereco endereco;
	
	@OneToMany(mappedBy="empresa")
	@JsonIgnore
	private Set<Vaga> vagas;


}