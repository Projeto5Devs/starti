package br.com.starti.controller;

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

@RestController
@RequestMapping("/vaga")
public class VagaController {
	
	@Autowired
	VagaService service;
	
	@GetMapping(produces={"application/json", "application/xml"})
	@ResponseStatus(HttpStatus.OK)
	public List<VagaVO>findAll(){
		return service.buscarTodos();
	}
	
	@GetMapping(value="/{id}", produces={"application/json", "application/xml"})
	@ResponseStatus(HttpStatus.OK)
	public VagaVO findById(@PathVariable("id") Long id) {
		return service.buscarPorId(id);
	}
	
	@PostMapping(consumes= {"application/json", "application/xml"},produces={"application/json", "application/xml"})
	@ResponseStatus(HttpStatus.CREATED)
	public VagaVO create(@Valid @RequestBody VagaVO vaga) {
		return service.inserir(vaga);
	}
	
	@PutMapping(consumes= {"application/json", "application/xml"},produces={"application/json", "application/xml"})
	@ResponseStatus(HttpStatus.OK)
	public VagaVO update(@Valid @RequestBody VagaVO vaga) {
		return service.atualizar(vaga);
	}
	
	@DeleteMapping(value="/{id}")
	@ResponseStatus(HttpStatus.OK)
	public void delete(@PathVariable("id") Long id) {
		service.deletar(id);
	}
}
