package br.com.starti.domain.entity;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Objects;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;


@Entity
@Table(name="usuario")
public class Usuario implements UserDetails, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="id_usuario")
	private Long idUsuario;
	private String username;
	private String password;
	private Boolean accountNonExpired;
	private Boolean accountNonLocked;
	private Boolean credentialNonExpired;
	private Boolean enabled;
	private LocalDate ultimoLogin;
	
	@OneToOne(mappedBy="userId", cascade = CascadeType.ALL)
	@JsonIgnore
	private Empresa empresa;
	
	@OneToOne(mappedBy="userId", cascade = CascadeType.ALL)
	@JsonIgnore
	private PessoaFisica pessoaFisica;

	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "permissoes_usuario", joinColumns = {
	@JoinColumn(name = "id_usuario") }, inverseJoinColumns = { @JoinColumn(name = "id_permission") })
	private List<Permission> permissoes;

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return this.permissoes;
	}
	
	

	public Long getIdUsuario() {
		return idUsuario;
	}



	public void setIdUsuario(Long idUsuario) {
		this.idUsuario = idUsuario;
	}



	public void setAccountNonExpired(Boolean accountNonExpired) {
		this.accountNonExpired = accountNonExpired;
	}



	public void setAccountNonLocked(Boolean accountNonLocked) {
		this.accountNonLocked = accountNonLocked;
	}



	public void setCredentialNonExpired(Boolean credentialNonExpired) {
		this.credentialNonExpired = credentialNonExpired;
	}



	public void setEnabled(Boolean enabled) {
		this.enabled = enabled;
	}



	public void setEmpresa(Empresa empresa) {
		this.empresa = empresa;
	}


	

	public LocalDate getUltimoLogin() {
		return ultimoLogin;
	}



	public void setUltimoLogin(LocalDate ultimoLogin) {
		this.ultimoLogin = ultimoLogin;
	}



	@Override
	public String getPassword() {
		return this.password;
	}

	@Override
	public String getUsername() {
		return this.username;
	}

	@Override
	public boolean isAccountNonExpired() {
		return this.accountNonExpired;
	}

	@Override
	public boolean isAccountNonLocked() {
		return this.accountNonLocked;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return this.credentialNonExpired;
	}

	@Override
	public boolean isEnabled() {
		return this.enabled;
	}

	public List<String> getRoles() {

		List<String> roles = new ArrayList<>();
		for (Permission permissao : this.permissoes) {
			roles.add(permissao.getDescricao());
		}
		return roles;

	}

	@Override
	public int hashCode() {
		return Objects.hash(accountNonExpired, accountNonLocked, credentialNonExpired, empresa, enabled, idUsuario,
				password, permissoes, pessoaFisica, ultimoLogin, username);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Usuario other = (Usuario) obj;
		return Objects.equals(accountNonExpired, other.accountNonExpired)
				&& Objects.equals(accountNonLocked, other.accountNonLocked)
				&& Objects.equals(credentialNonExpired, other.credentialNonExpired)
				&& Objects.equals(empresa, other.empresa) && Objects.equals(enabled, other.enabled)
				&& Objects.equals(idUsuario, other.idUsuario) && Objects.equals(password, other.password)
				&& Objects.equals(permissoes, other.permissoes) && Objects.equals(pessoaFisica, other.pessoaFisica)
				&& Objects.equals(ultimoLogin, other.ultimoLogin) && Objects.equals(username, other.username);
	}
	
	
	

}
