package br.com.starti.domain.entity;

import java.util.Date;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.Table;




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
	
	
	@Column(name="data_inscricao")
	private Date dataInscricao;

	public ChaveCompostaInscricao getIdInscricao() {
		return idInscricao;
	}

	public void setIdInscricao(ChaveCompostaInscricao idInscricao) {
		this.idInscricao = idInscricao;
	}

	public Date getDataInscricao() {
		return dataInscricao;
	}

	public void setDataInscricao(Date dataInscricao) {
		this.dataInscricao = dataInscricao;
	}

	public PessoaFisica getPessoafisica() {
		return pessoafisica;
	}

	public void setPessoafisica(PessoaFisica pessoafisica) {
		this.pessoafisica = pessoafisica;
	}

	public Vaga getVaga() {
		return vaga;
	}

	public void setVaga(Vaga vaga) {
		this.vaga = vaga;
	}


	



	
	
}