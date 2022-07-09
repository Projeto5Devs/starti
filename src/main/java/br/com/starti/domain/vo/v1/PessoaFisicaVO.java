package br.com.starti.domain.vo.v1;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;
import java.util.Set;

import org.springframework.hateoas.RepresentationModel;

import com.github.dozermapper.core.Mapping;

import br.com.starti.domain.entity.Contato;
import br.com.starti.domain.entity.Endereco;
import br.com.starti.domain.entity.Inscricao;
import br.com.starti.domain.entity.Login;
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
	private Login login;
	private Endereco endereco;
    Set<Inscricao> inscricoes;
    
    @Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		PessoaFisicaVO other = (PessoaFisicaVO) obj;
		return Objects.equals(contato, other.contato) && Objects.equals(cpf, other.cpf)
				&& Objects.equals(dataDeNascimento, other.dataDeNascimento) && Objects.equals(endereco, other.endereco)
				&& Objects.equals(inscricoes, other.inscricoes) && Objects.equals(id, other.id)
				&& Objects.equals(login, other.login) && Objects.equals(nome, other.nome)
				&& Objects.equals(sobrenome, other.sobrenome);
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result
				+ Objects.hash(contato, cpf, dataDeNascimento, endereco, inscricoes, id, login, nome, sobrenome);
		return result;
	}
}
