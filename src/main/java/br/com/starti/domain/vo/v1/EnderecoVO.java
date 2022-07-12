package br.com.starti.domain.vo.v1;

import java.io.Serializable;
import java.util.Objects;

import org.springframework.hateoas.RepresentationModel;

import com.github.dozermapper.core.Mapping;

import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class EnderecoVO extends RepresentationModel<EnderecoVO> implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Mapping("id_Endereco")
	private Long key;
	private String rua;
	private String numero;
	private String complemento;
	private String bairro;
	private String cep;
	private String cidade;
	private String uf;
	
	@Override
	public int hashCode() {
		return Objects.hash(key, rua, numero, complemento, bairro, cep, cidade, uf);
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		EnderecoVO other = (EnderecoVO) obj;
		return Objects.equals(rua, other.rua) 
				&& Objects.equals(key, other.key)
				&& Objects.equals(numero, other.numero)
				&& Objects.equals(complemento, other.complemento) 
				&& Objects.equals(bairro, other.bairro)
				&& Objects.equals(cep, other.cep)
				&& Objects.equals(cidade, other.cidade)
				&& Objects.equals(uf, other.uf);
	}
	
	public Long getKey() {
		return null;
	}
}
