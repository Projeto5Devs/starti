package br.com.starti.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import br.com.starti.domain.entity.Empresa;
import br.com.starti.domain.service.EmpresaService;


@RestController
@RequestMapping("/empresas")
public class EmpresaController {

	@Autowired
	EmpresaService empresaService;
	
	@GetMapping(produces=MediaType.APPLICATION_JSON_VALUE)
	public List<Empresa>findAll(){
		return empresaService.buscarTodos();
	}
	
	@GetMapping(value="/{id}", produces=MediaType.APPLICATION_JSON_VALUE)
	public Empresa findById(@PathVariable("id")Integer id) {
		return empresaService.buscarPorId(id);
	}
	
	@PostMapping(consumes=MediaType.APPLICATION_JSON_VALUE, produces=MediaType.APPLICATION_JSON_VALUE)
	public Empresa create(@Valid @RequestBody Empresa empresa) {
		return empresaService.inserir(empresa);
	}

	@PutMapping(consumes=MediaType.APPLICATION_JSON_VALUE, produces=MediaType.APPLICATION_JSON_VALUE)
	public Empresa update(@RequestBody Empresa empresa) {
		return empresaService.atualizar(empresa);	
	}
	
	@DeleteMapping(value="/{id}")
	public void delete(@PathVariable("id")Integer id) {
		empresaService.deletar(id);
	}
	
}
