package br.com.starti.domain.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import lombok.Data;

@Embeddable
@Data
public class ChaveCompostaInscricao implements Serializable{

	private static final long serialVersionUID = 1L;

	@Column(name="id_pessoafisica")
	Long idPessoaFisica;
	
	@Column(name="id_vaga")
	Long idVaga;
}
