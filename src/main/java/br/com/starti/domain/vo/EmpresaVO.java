package br.com.starti.domain.vo;

import java.io.Serializable;

import lombok.Data;


@Data
public class EmpresaVO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	

	private Long idEmpresa;
	private String nomeFantasia;
	private String razaoSocial;
	private String segmento;
	private String cnpj;

}
