package br.com.starti.domain.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.FutureOrPresent;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;

import lombok.Data;

@Data
@Entity
@Table(name ="vaga")
public class Vaga implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_vaga")
	private Long id;
	
	@NotBlank
	@Size(max=255)
	@Column(name = "descricao_vaga")
	private String descricao;
	
	@Positive
	@Column(name = "salario_vaga")
	private double salario;
	
	@NotBlank
	@Size(max=15)
	@Column(name = "modalidade_vaga")
	private String modalidade;
	
	@NotNull
	@FutureOrPresent 
	@Column(name = "prazo_vaga")
	private Date prazo;
	
	@NotBlank
	@Size(max=150)
	@Column(name = "cargo_vaga")
	private String cargo;
	
	@ManyToOne
    @JoinColumn(name="empresa_id_empresa", nullable=false)
	private Empresa empresa;
	
	@OneToMany(mappedBy = "vaga")
    Set<Inscricao> inscricoes;
}
