package br.com.starti.domain.vo.v1;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import org.springframework.hateoas.RepresentationModel;

import com.github.dozermapper.core.Mapping;

import br.com.starti.domain.entity.Contato;
import br.com.starti.domain.entity.Endereco;
import br.com.starti.domain.entity.Inscricao;
import br.com.starti.domain.entity.Usuario;
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
	private Usuario userId;
	private List<Inscricao> inscricoes;
	
}
