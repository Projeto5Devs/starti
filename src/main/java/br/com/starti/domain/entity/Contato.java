package br.com.starti.domain.entity;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import lombok.Data;

@Data
@Embeddable
public class Contato {
	
	@NotBlank
	@Column
	@Size(max=25)
	private String telefone;
	
	@NotBlank
	@Email
	@Column
	@Size(max=80)
	private String email;
	
	@Size(max=150)
	@Column
	private String website;
}
