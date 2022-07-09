package br.com.starti.domain.vo;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;

import org.springframework.hateoas.RepresentationModel;

import com.github.dozermapper.core.Mapping;

import br.com.starti.domain.entity.Contato;
import br.com.starti.domain.entity.Endereco;
import br.com.starti.domain.entity.Inscricao;
import lombok.Data;

@Data
public class PessoaFisicaVO extends RepresentationModel<PessoaFisicaVO> implements Serializable{

	private static final long serialVersionUID = 1L;

	@Mapping("idPessoaFisica")
	private Long id;
	private String nome;
	private String sobrenome;
	private String cpf;
	private Date dataDeNascimento;
	private Contato contato;
	private Endereco endereco;
    Set<Inscricao> inscricoes;
    
}
