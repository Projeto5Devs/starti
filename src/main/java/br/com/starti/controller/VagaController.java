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

import br.com.starti.domain.vo.v1.VagaVO;
import br.com.starti.service.VagaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name="Endpoint Vaga")
@RestController
@RequestMapping("/vaga/v1")
public class VagaController {
	
	@Autowired
	VagaService service;
	
	@GetMapping(produces={"application/json", "application/xml"})
	@Operation(summary="Listar todas as vagas")
	@ResponseStatus(HttpStatus.OK)
	public List<VagaVO>findAll(){
		List<VagaVO> vagaVO = service.buscarTodos();
		vagaVO.stream().forEach(p -> p.add(linkTo(methodOn(VagaController.class).findById(p.getKey())).withSelfRel()));
		return vagaVO;
	}
	
	@GetMapping(value="/{id}", produces={"application/json", "application/xml"})
	@Operation(summary="Procurar vaga por ID")
	@ResponseStatus(HttpStatus.OK)
	public VagaVO findById(@PathVariable("id") Long id) {
		VagaVO vagaVO = service.buscarPorId(id);
		vagaVO.add(linkTo(methodOn(VagaController.class).findById(id)).withSelfRel());
		return vagaVO;
	}
	
	@PostMapping(consumes= {"application/json", "application/xml"},produces={"application/json", "application/xml"})
	@Operation(summary="Cadastrar nova vaga")
	@ResponseStatus(HttpStatus.CREATED)
	public VagaVO create(@Valid @RequestBody VagaVO vaga) {
		VagaVO vagaVO = service.inserir(vaga);
		vagaVO.add(linkTo(methodOn(VagaController.class).findById(vagaVO.getKey())).withSelfRel());
		return vagaVO;
	}
	
	@PutMapping(consumes= {"application/json", "application/xml"},produces={"application/json", "application/xml"})
	@Operation(summary="Atualizar dados da vaga")
	@ResponseStatus(HttpStatus.OK)
	public VagaVO update(@Valid @RequestBody VagaVO vaga) {
		VagaVO vagaVO = service.atualizar(vaga);	
		vagaVO.add(linkTo(methodOn(VagaController.class).findById(vagaVO.getKey())).withSelfRel());
		return vagaVO;
	}
	
	@DeleteMapping(value="/{id}")
	@Operation(summary="Deletar vaga")
	@ResponseStatus(HttpStatus.OK)
	public void delete(@PathVariable("id") Long id) {
		service.deletar(id);
	}
}
