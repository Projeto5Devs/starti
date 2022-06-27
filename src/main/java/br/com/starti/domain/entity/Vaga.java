package br.com.starti.domain.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name ="tb_vaga")
@Data
@NoArgsConstructor

public class Vaga implements Serializable{
	
	/**
	 * Cadastrar Vaga
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_vaga")
	@NotBlank
	private Long id;
	
	@NotBlank
	@Size(max=50000)
	@Column(name = "descricao_vaga")
	private String descricao;
	
	@NotBlank
	@Size(max=100)
	@Column(name = "salario_vaga")
	private double salario;
	
	@NotBlank
	@Size(max=200)
	@Column(name = "modalidade_vaga")
	private String modalidade;
	
	@NotBlank
	@Size(max=8)
	@Column(name = "prazo_vaga")
	private String prazo;
	
	@NotBlank
	@Size(max=150)
	@Column(name = "cargo_vaga")
	private String cargo;
	
	@NotBlank
	@Size(max=50000)
	@Column(name = "empresa_vaga")
	private String empresa;
	
}
