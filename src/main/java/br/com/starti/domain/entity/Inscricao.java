//package br.com.starti.domain.entity;
//
//import java.io.Serializable;
//import java.util.Date;
//
//import javax.persistence.Column;
//import javax.persistence.Entity;
//import javax.persistence.JoinColumn;
//import javax.persistence.ManyToOne;
//import javax.persistence.Table;
//import javax.validation.constraints.NotBlank;
//
//import lombok.Data;
//
//@Data
//@Entity
//@Table(name="inscricao")
//public class Inscricao implements Serializable{
//	
//	private static final long serialVersionUID = 1L;
//	
////	@ManyToOne
////	@JoinColumn(name="id_candidato", table="candidato")
////	private int candidato;
////	
////	@ManyToOne
////	@JoinColumn(name="id_empresa", table="empresa")
////	private int empresa;
//	
//	@NotBlank
//	@Column(name="data_inscricao")
//	private Date dataInscricao;
//}