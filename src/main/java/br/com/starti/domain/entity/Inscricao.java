package br.com.starti.domain.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

import lombok.Data;

@Data
@Entity
@Table(name="inscricao")
public class Inscricao implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@ManyToOne
	@JoinColumn(name="candidato_id_candidato", referencedColumnName="idCandidato")
	private Candidato candidato;
	
	@ManyToOne
	@JoinColumn(name="empresa_id_empresa", referencedColumnName="idEmpresa")
	private Empresa empresa;
	
	@NotBlank
	@Column
	private Date dataInscricao;
}