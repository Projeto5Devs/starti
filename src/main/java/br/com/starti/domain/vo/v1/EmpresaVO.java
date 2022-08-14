package br.com.starti.domain.vo.v1;

import java.io.Serializable;
import java.util.Objects;

import org.springframework.hateoas.RepresentationModel;

import com.github.dozermapper.core.Mapping;

import br.com.starti.domain.entity.Contato;
import br.com.starti.domain.entity.Endereco;
import br.com.starti.domain.entity.Usuario;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class EmpresaVO extends RepresentationModel<EmpresaVO> implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Mapping("idEmpresa")
	private Long key;
	private String nomeFantasia;
	private String razaoSocial;
	private String segmento;
	private String cnpj;
	private Usuario userId;
	private Contato contato;
	private Endereco endereco;
	
	
	@Override
	public int hashCode() {
		return Objects.hash(cnpj, key, nomeFantasia, razaoSocial, segmento);
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		EmpresaVO other = (EmpresaVO) obj;
		return Objects.equals(cnpj, other.cnpj) && Objects.equals(key, other.key)
				&& Objects.equals(nomeFantasia, other.nomeFantasia) && Objects.equals(razaoSocial, other.razaoSocial)
				&& Objects.equals(segmento, other.segmento);
	}



}