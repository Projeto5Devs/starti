package br.com.starti.domain.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name ="tb_usuarios")
@Data
@NoArgsConstructor
public class Usuario implements Serializable {

	/**
	 * Cadastrar usuario
	 */
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_usuario")
	@NotBlank
	private Long id;
	
	@NotBlank
	@Size(max=80)
	@Column(name = "nome_usuario")
	private String nome;
	
	@NotBlank
	@Size(max=11)
	@Column(name = "telefone_usuario")
	private String telefone;

}
