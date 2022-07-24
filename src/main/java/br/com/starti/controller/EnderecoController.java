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

import br.com.starti.domain.vo.v1.EnderecoVO;
import br.com.starti.service.EnderecoService;
import io.swagger.v3.oas.annotations.Operation;

@RestController
@RequestMapping("/endereco/v1")
public class EnderecoController {

	@Autowired
	EnderecoService enderecoService;

	@GetMapping(produces = { "application/json", "application/xml" })
	@Operation(summary="Listar todas as empresas")
	@ResponseStatus(HttpStatus.OK)
	public List<EnderecoVO> findAll() {
		List<EnderecoVO> enderecoVO = enderecoService.buscarTodos();
		enderecoVO.stream()
				.forEach(p -> p.add(linkTo(methodOn(EnderecoController.class).findById(p.getKey())).withSelfRel()));
		return enderecoVO;
	}

	@GetMapping(value = "/{id}", produces = { "application/json", "application/xml" })
	@Operation(summary="Procurar empresa por ID")
	@ResponseStatus(HttpStatus.OK)
	public EnderecoVO findById(@PathVariable("id") Long id) {
		EnderecoVO enderecoVO = enderecoService.buscarPorId(id);
		enderecoVO.add(linkTo(methodOn(EnderecoController.class).findById(id)).withSelfRel());
		return enderecoVO;
	}

	@PostMapping(consumes = { "application/json", "application/xml" }, produces = { "application/json","application/xml" })
	@Operation(summary="Cadastrar nova empresa")
	@ResponseStatus(HttpStatus.CREATED)
	public EnderecoVO create(@Valid @RequestBody EnderecoVO endereco) {
		EnderecoVO enderecoVO = enderecoService.inserir(endereco);
		enderecoVO.add(linkTo(methodOn(EnderecoController.class).findById(enderecoVO.getKey())).withSelfRel());
		return enderecoVO;
	}

	@PutMapping(consumes = { "application/json", "application/xml" }, produces = { "application/json","application/xml" })
	@Operation(summary="Atualizar dados da empresa")
	@ResponseStatus(HttpStatus.OK)
	public EnderecoVO update(@Valid @RequestBody EnderecoVO endereco) {
		EnderecoVO enderecoVO = enderecoService.atualizar(endereco);
		enderecoVO.add(linkTo(methodOn(EnderecoController.class).findById(enderecoVO.getKey())).withSelfRel());
		return enderecoVO;
	}

	@DeleteMapping(value = "/{id}")
	@Operation(summary="Deletar empresa")
	@ResponseStatus(HttpStatus.OK)
	public void delete(@PathVariable("id") Long id) {
		enderecoService.deletar(id);
	}
}