package br.com.starti.controller;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

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
	public ResponseEntity<CollectionModel<VagaVO>> findAll(
			@RequestParam(value="page", defaultValue="0") int page,
			@RequestParam(value="limit", defaultValue="10") int limit,
			@RequestParam(value="direction", defaultValue="asc") String direction){
		var sortDirection = "desc".equalsIgnoreCase(direction)?Direction.DESC:Direction.ASC;
		Pageable pageable = PageRequest.of(page, limit, Sort.by(sortDirection, "id"));
		Page<VagaVO> vagaVO = service.buscarTodos(pageable);	
		vagaVO.stream().forEach(p -> p.add(linkTo(methodOn(VagaController.class).findById(p.getKey())).withSelfRel()));
		return ResponseEntity.ok(CollectionModel.of(vagaVO));
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
	
	@GetMapping(value="/buscarPorCargo/{cargo}", produces={"application/json", "application/xml"})
	@Operation(summary="Listar vagas por cargo")
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<CollectionModel<VagaVO>> findVagaByCargo(
			@PathVariable("cargo") String cargo,
			@RequestParam(value="page", defaultValue="0") int page,
			@RequestParam(value="limit", defaultValue="10") int limit,
			@RequestParam(value="direction", defaultValue="asc") String direction){
		var sortDirection = "desc".equalsIgnoreCase(direction)?Direction.DESC:Direction.ASC;
		Pageable pageable = PageRequest.of(page, limit, Sort.by(sortDirection, "cargo"));
		Page<VagaVO> vagaVO = service.findByCargo(cargo, pageable);	
		vagaVO.stream().forEach(p -> p.add(linkTo(methodOn(VagaController.class).findById(p.getKey())).withSelfRel()));
		return ResponseEntity.ok(CollectionModel.of(vagaVO));
	}
	
	@GetMapping(value="/buscarPorModalidade/{modalidade}", produces={"application/json", "application/xml"})
	@Operation(summary="Listar vagas por modalidade")
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<CollectionModel<VagaVO>> findVagaByModalidade(
			@PathVariable("modalidade") String modalidade,
			@RequestParam(value="page", defaultValue="0") int page,
			@RequestParam(value="limit", defaultValue="10") int limit,
			@RequestParam(value="direction", defaultValue="asc") String direction){
		var sortDirection = "desc".equalsIgnoreCase(direction)?Direction.DESC:Direction.ASC;
		Pageable pageable = PageRequest.of(page, limit, Sort.by(sortDirection, "cargo"));
		Page<VagaVO> vagaVO = service.findByModalidade(modalidade, pageable);	
		vagaVO.stream().forEach(p -> p.add(linkTo(methodOn(VagaController.class).findById(p.getKey())).withSelfRel()));
		return ResponseEntity.ok(CollectionModel.of(vagaVO));
	}
	
	@GetMapping(value="/buscarPorTipo/{tipo}", produces={"application/json", "application/xml"})
	@Operation(summary="Listar vagas por tipo")
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<CollectionModel<VagaVO>> findVagaByTipo(
			@PathVariable("tipo") String tipo,
			@RequestParam(value="page", defaultValue="0") int page,
			@RequestParam(value="limit", defaultValue="10") int limit,
			@RequestParam(value="direction", defaultValue="asc") String direction){
		var sortDirection = "desc".equalsIgnoreCase(direction)?Direction.DESC:Direction.ASC;
		Pageable pageable = PageRequest.of(page, limit, Sort.by(sortDirection, "cargo"));
		Page<VagaVO> vagaVO = service.findByTipo(tipo, pageable);	
		vagaVO.stream().forEach(p -> p.add(linkTo(methodOn(VagaController.class).findById(p.getKey())).withSelfRel()));
		return ResponseEntity.ok(CollectionModel.of(vagaVO));
	}
}
