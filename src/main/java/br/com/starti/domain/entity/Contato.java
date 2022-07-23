package br.com.starti.domain.entity;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.Data;

@Data
@Embeddable
public class Contato {
	
	@NotNull
	@Column
	@Size(max=25)
	private String telefone;
	
	@NotNull
	@Email
	@Size(max=80)
	@Column(unique= true)
	private String email;
	
	@Size(max=150)
	@Column
	private String website;


	
}
