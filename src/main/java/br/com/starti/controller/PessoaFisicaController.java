package br.com.starti.controller;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.util.HashSet;
import java.util.Set;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.starti.domain.entity.Permission;
import br.com.starti.domain.entity.Usuario;
import br.com.starti.domain.enums.TipoPermissao;
import br.com.starti.domain.vo.v1.PessoaFisicaVO;
import br.com.starti.service.PessoaFisicaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name="Endpoint Pessoa Física")
@RestController
@RequestMapping("/pessoafisica/v1")
public class PessoaFisicaController {

	@Autowired
	PessoaFisicaService service;
	
	@GetMapping(produces={"application/json", "application/xml"})
	@Operation(summary="Listar todas as pessoas")
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<CollectionModel<PessoaFisicaVO>> findAll(
			@RequestParam(value="page", defaultValue="0") int page,
			@RequestParam(value="limit", defaultValue="10") int limit,
			@RequestParam(value="direction", defaultValue="asc") String direction){
		var sortDirection = "desc".equalsIgnoreCase(direction)?Direction.DESC:Direction.ASC;
		Pageable pageable = PageRequest.of(page, limit, Sort.by(sortDirection, "nome"));
		Page<PessoaFisicaVO> pessoaFisicaVO = service.buscarTodos(pageable);
		pessoaFisicaVO.stream().forEach(p -> p.add(linkTo(methodOn(PessoaFisicaController.class).findById(p.getKey())).withSelfRel()));
		return ResponseEntity.ok(CollectionModel.of(pessoaFisicaVO));
	}
	
	@GetMapping(value="/{id}", produces={"application/json", "application/xml"})
	@Operation(summary="Procurar pessoa por ID")
	@ResponseStatus(HttpStatus.OK)
	public PessoaFisicaVO findById(@PathVariable("id")Long id) {
		PessoaFisicaVO pessoaFisicaVO = service.buscarPorId(id);
		pessoaFisicaVO.add(linkTo(methodOn(PessoaFisicaController.class).findById(id)).withSelfRel());
		return pessoaFisicaVO;
	}
	
	@PostMapping(consumes= {"application/json", "application/xml"},produces={"application/json", "application/xml"})
	@Operation(summary="Cadastrar nova pessoa")
	@ResponseStatus(HttpStatus.CREATED)
	public PessoaFisicaVO create(@Valid @RequestBody PessoaFisicaVO pessoaFisica) {
		pessoaFisica.getUserId().setPassword(new BCryptPasswordEncoder().encode(pessoaFisica.getUserId().getPassword()));

		
		
		PessoaFisicaVO pessoaFisicaVO = service.inserir(pessoaFisica);
		pessoaFisicaVO.add(linkTo(methodOn(PessoaFisicaController.class).findById(pessoaFisicaVO.getKey())).withSelfRel());
		return pessoaFisicaVO;
	}

	@PutMapping(consumes= {"application/json", "application/xml"},produces={"application/json", "application/xml"})
	@Operation(summary="Atualizar dados da pessoa")
	@ResponseStatus(HttpStatus.OK)
	public PessoaFisicaVO update(@Valid @RequestBody PessoaFisicaVO pessoaFisica) {
		PessoaFisicaVO pessoaFisicaVO = service.atualizar(pessoaFisica);	
		pessoaFisicaVO.add(linkTo(methodOn(PessoaFisicaController.class).findById(pessoaFisicaVO.getKey())).withSelfRel());
		return pessoaFisicaVO;
	}
	
	@DeleteMapping(value="/{id}")
	@Operation(summary="Deletar pessoa")
	@ResponseStatus(HttpStatus.OK)
	public void delete(@PathVariable("id")Long id) {
		service.deletar(id);
	}
	
}
