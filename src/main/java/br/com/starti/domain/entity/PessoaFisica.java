package br.com.starti.domain.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import lombok.Data;

@Data
@Entity
@Table(name = "pessoa_fisica")
public class PessoaFisica implements UserDetails, Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id_pessoa_fisica")
	private Long idPessoaFisica;

	@NotBlank
	@Column
	@Size(max = 20)
	private String nome;

	@NotBlank
	@Column
	@Size(max = 60)
	private String sobrenome;

	@NotBlank
	@Column
	@Size(max = 14)
	private String cpf;

	@NotNull
	@Past
	@Column(name = "data_de_nascimento")
	private Date dataDeNascimento;

	@Embedded
	private Contato contato;

	@Embedded
	private Login login;

	private Boolean accountNonExpired;
	private Boolean accountNonLocked;
	private Boolean credentialNonExpired;
	private Boolean enabled;

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "endereco_id_endereco", referencedColumnName = "id_endereco")
	private Endereco endereco;

	@OneToMany(mappedBy = "pessoafisica")
	Set<Inscricao> inscricoes;

	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "permissoes_pessoa_fisica", joinColumns = {
	@JoinColumn(name = "id_pessoa_fisica") }, inverseJoinColumns = { @JoinColumn(name = "id_permission") })
	private List<Permission> permissoes;

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return this.permissoes;
	}

	@Override
	public String getPassword() {
		return this.login.getSenha();
	}

	@Override
	public String getUsername() {
		return this.contato.getEmail();
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

}
