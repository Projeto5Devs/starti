package br.com.starti.domain.vo.v1;

import java.io.Serializable;
import java.util.Date;

import org.springframework.hateoas.RepresentationModel;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.github.dozermapper.core.Mapping;

import br.com.starti.domain.entity.Contato;
import br.com.starti.domain.entity.Endereco;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PessoaFisicaVO extends RepresentationModel<PessoaFisicaVO> implements Serializable{

	private static final long serialVersionUID = 1L;

	@Mapping("idPessoaFisica")
	private Long key;
	private String nome;
	private String sobrenome;
	private String cpf;
	private Date dataDeNascimento;
	private Contato contato;
	private Endereco endereco;
	private UsuarioVO usuario;
    
	@JsonProperty("usuario")
	private void unpackNested(Long idUsuario) {
		this.usuario = new UsuarioVO();
		usuario.setKey(idUsuario);
	}
}
