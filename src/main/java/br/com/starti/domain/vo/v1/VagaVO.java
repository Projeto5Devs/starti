package br.com.starti.domain.vo.v1;

import java.io.Serializable;
import java.util.Date;

import org.springframework.hateoas.RepresentationModel;

import com.github.dozermapper.core.Mapping;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class VagaVO extends RepresentationModel<VagaVO> implements Serializable{

	private static final long serialVersionUID = 1L;

	@Mapping("id")
	private Long key;
	private String descricao;
	private double salario;
	private String modalidade;
	private Date prazo;
	private String cargo;
	private EmpresaVO empresa;

}
