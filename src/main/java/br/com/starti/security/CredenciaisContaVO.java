package br.com.starti.security;

import java.io.Serializable;
import java.util.Objects;



public class CredenciaisContaVO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String username;
	private String senha;
	
	
	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getSenha() {
		return senha;
	}
	public void setSenha(String senha) {
		this.senha = senha;
	}
	@Override
	public int hashCode() {
		return Objects.hash(senha, username);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		CredenciaisContaVO other = (CredenciaisContaVO) obj;
		return Objects.equals(senha, other.senha) && Objects.equals(username, other.username);
	}


	
	
	
	

}
