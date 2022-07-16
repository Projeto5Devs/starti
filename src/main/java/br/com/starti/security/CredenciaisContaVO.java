package br.com.starti.security;

import java.io.Serializable;

import lombok.Data;

@Data
public class CredenciaisContaVO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String email;
	private String senha;

}
