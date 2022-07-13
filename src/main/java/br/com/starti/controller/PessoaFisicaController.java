package br.com.starti.controller;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.starti.domain.vo.v1.PessoaFisicaVO;
import br.com.starti.service.PessoaFisicaService;

@RestController
@RequestMapping("/pessoafisica/v1")
public class PessoaFisicaController {

	@Autowired
	PessoaFisicaService service;
	
	@GetMapping(produces={"application/json", "application/xml"})
	@ResponseStatus(HttpStatus.OK)
	public List<PessoaFisicaVO>findAll(){
		List<PessoaFisicaVO> pessoaFisicaVO = service.buscarTodos();
		pessoaFisicaVO.stream().forEach(p -> p.add(linkTo(methodOn(PessoaFisicaController.class).findById(p.getKey())).withSelfRel()));
		return pessoaFisicaVO;
	}
	
	@GetMapping(value="/{id}", produces={"application/json", "application/xml"})
	@ResponseStatus(HttpStatus.OK)
	public PessoaFisicaVO findById(@PathVariable("id")Long id) {
		PessoaFisicaVO pessoaFisicaVO = service.buscarPorId(id);
		pessoaFisicaVO.add(linkTo(methodOn(PessoaFisicaController.class).findById(id)).withSelfRel());
		return pessoaFisicaVO;
	}
	
	@PostMapping(consumes= {"application/json", "application/xml"},produces={"application/json", "application/xml"})
	@ResponseStatus(HttpStatus.CREATED)
	public PessoaFisicaVO create(@Valid @RequestBody PessoaFisicaVO pessoaFisica) {
		PessoaFisicaVO pessoaFisicaVO = service.inserir(pessoaFisica);
		pessoaFisicaVO.add(linkTo(methodOn(PessoaFisicaController.class).findById(pessoaFisicaVO.getKey())).withSelfRel());
		return pessoaFisicaVO;
	}

	@PutMapping(consumes= {"application/json", "application/xml"},produces={"application/json", "application/xml"})
	@ResponseStatus(HttpStatus.OK)
	public PessoaFisicaVO update(@Valid @RequestBody PessoaFisicaVO pessoaFisica) {
		PessoaFisicaVO pessoaFisicaVO = service.atualizar(pessoaFisica);	
		pessoaFisicaVO.add(linkTo(methodOn(PessoaFisicaController.class).findById(pessoaFisicaVO.getKey())).withSelfRel());
		return pessoaFisicaVO;
	}
	
	@DeleteMapping(value="/{id}")
	@ResponseStatus(HttpStatus.OK)
	public void delete(@PathVariable("id")Long id) {
		service.deletar(id);
	}
	
}
