package br.com.starti.domain.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;

import javax.persistence.CascadeType;
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
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;


@Entity
@Table(name ="vaga")
public class Vaga implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_vaga")
	private Long id;
	
	@NotNull
	@Size(max=2000)
	@Column(name = "descricao_vaga")
	private String descricao;
	
	@Positive
	@Column(name = "salario_vaga")
	private double salario;
	
	@NotNull
	@Size(max=15)
	@Column(name = "modalidade_vaga")
	private String modalidade;
	
	@NotNull
	@Size(max=15)
	@Column(name = "tipo_vaga")
	private String tipo;
	
	@NotNull
	@FutureOrPresent 
	@Column(name = "prazo_vaga")
	private Date prazo;
	
	@Column(name = "data_criacao")
	private Date dataCriacao;
	
	@NotNull
	@Size(max=150)
	@Column(name = "cargo_vaga")
	private String cargo;
	
	@ManyToOne
    @JoinColumn(name="id_empresa", nullable=false)
	private Empresa empresa;
	
	@JsonIgnore
	@OneToMany(cascade = CascadeType.ALL,mappedBy = "vaga")
    Set<Inscricao> inscricoes;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public double getSalario() {
		return salario;
	}

	public void setSalario(double salario) {
		this.salario = salario;
	}

	public String getModalidade() {
		return modalidade;
	}

	public void setModalidade(String modalidade) {
		this.modalidade = modalidade;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public Date getPrazo() {
		return prazo;
	}

	public void setPrazo(Date prazo) {
		this.prazo = prazo;
	}

	public Date getDataCriacao() {
		return dataCriacao;
	}

	public void setDataCriacao(Date dataCriacao) {
		this.dataCriacao = dataCriacao;
	}

	public String getCargo() {
		return cargo;
	}

	public void setCargo(String cargo) {
		this.cargo = cargo;
	}

	public Empresa getEmpresa() {
		return empresa;
	}

	public void setEmpresa(Empresa empresa) {
		this.empresa = empresa;
	}
	
	
}
