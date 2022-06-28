package br.com.starti.domain.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import lombok.Data;

@Embeddable
@Data
public class ChaveCompostaInscricao implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Column(name="candidato_id_candidato")
	int idCandidato;
	
	@Column(name="vaga_id_vaga")
	int idVaga;
}
