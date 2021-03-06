package br.com.starti.domain.entity;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import org.springframework.security.core.GrantedAuthority;

import br.com.starti.domain.enums.TipoPermissao;
import lombok.Data;

@Data
@Entity
@Table(name="permissao")
public class Permission implements GrantedAuthority, Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idPermissao;
	@Enumerated(EnumType.STRING)
	private TipoPermissao descricao;
	
	@ManyToMany(mappedBy="permissoes")
	private Set<Usuario> usuarios;
	
	
	@Override
	public String getAuthority() {
		return this.descricao.toString();
	}

}
