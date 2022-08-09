package br.com.starti.domain.entity;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;

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
import javax.validation.constraints.NotNull;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
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
	@Column(unique = true, nullable = false)
	@NotNull
	private String username;
	@NotNull
	private String password;
	private LocalDate ultimoLogin;
	@OneToOne(mappedBy="userId", cascade = CascadeType.ALL)
	@JsonIgnore
	private Empresa empresa;
	@OneToOne(mappedBy="userId", cascade = CascadeType.ALL)
	@JsonIgnore
	private PessoaFisica pessoaFisica;
	@ManyToMany(fetch = FetchType.EAGER)
	@JsonIgnore
	@JoinTable(name = "permissao_usuario", joinColumns = {
	@JoinColumn(name = "id_usuario") }, inverseJoinColumns = { @JoinColumn(name = "id_permissao") })
	private Set<Permission> permissoes;

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
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}

	public List<String> getRoles() {

		List<String> roles = new ArrayList<>();
		for (Permission permissao : this.permissoes) {
			roles.add(permissao.getDescricao().toString());
		}
		return roles;

	}


	
	
	
	
	

}
