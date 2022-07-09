package br.com.starti.domain.vo;

import java.io.Serializable;
import java.util.Date;

import org.springframework.hateoas.RepresentationModel;

import com.github.dozermapper.core.Mapping;

import br.com.starti.domain.entity.Empresa;
import lombok.Data;

@Data
public class VagaVO extends RepresentationModel<VagaVO> implements Serializable{

	private static final long serialVersionUID = 1L;

	@Mapping("id")
	private Long id;
	private String descricao;
	private double salario;
	private String modalidade;
	private Date prazo;
	private String cargo;
	private Empresa empresa;

}
