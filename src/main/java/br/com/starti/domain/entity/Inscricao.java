package br.com.starti.domain.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;




@Entity
@Table(name="inscricao")
public class Inscricao{
	
	@EmbeddedId
	ChaveCompostaInscricao idInscricao;
	
	@ManyToOne
	@MapsId("idPessoafisica")
	@JoinColumn(name="id_pessoafisica")
	PessoaFisica pessoafisica;
	
	@ManyToOne
	@MapsId("idVaga")
	@JoinColumn(name="id_vaga")
	Vaga vaga;
	
	@NotBlank
	@Column(name="data_inscricao")
	private Date dataInscricao;
}