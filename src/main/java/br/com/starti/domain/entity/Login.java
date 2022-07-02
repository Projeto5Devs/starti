package br.com.starti.domain.entity;

import java.time.LocalDateTime;

import javax.persistence.Embeddable;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import lombok.Data;

@Data
@Embeddable
public class Login {

	@Size(max=45)
	@NotBlank
	private String senha;
	
	private LocalDateTime ultimoLogin;


		
	
}
