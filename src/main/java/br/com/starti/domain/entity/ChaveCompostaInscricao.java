package br.com.starti.domain.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import lombok.Data;

@Data
@Embeddable
public class ChaveCompostaInscricao implements Serializable{

	private static final long serialVersionUID = 1L;

	@Column(name="id_pessoafisica")
	Long idPessoaFisica;
	
	@Column(name="id_vaga")
	Long idVaga;

	public Long getIdPessoaFisica() {
		return idPessoaFisica;
	}

	public void setIdPessoaFisica(Long idPessoaFisica) {
		this.idPessoaFisica = idPessoaFisica;
	}

	public Long getIdVaga() {
		return idVaga;
	}

	public void setIdVaga(Long idVaga) {
		this.idVaga = idVaga;
	}
	
	
	
	
}
