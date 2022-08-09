package br.com.starti.domain.vo.v1;

import java.io.Serializable;

import org.springframework.hateoas.RepresentationModel;

import com.github.dozermapper.core.Mapping;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UsuarioVO extends RepresentationModel<UsuarioVO> implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Mapping("idUsuario")
	private Long key;
	private String username;
	private String password;
	
}
