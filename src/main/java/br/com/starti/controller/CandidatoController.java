package br.com.starti.controller;

import java.util.List;

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

import br.com.starti.domain.entity.Candidato;
import br.com.starti.domain.service.CandidatoService;

@RestController
@RequestMapping("/candidato")
public class CandidatoController {

	@Autowired
	CandidatoService service;
	
	@GetMapping(produces=MediaType.APPLICATION_JSON_VALUE)
	public List<Candidato>findAll(){
		return service.buscarTodos();
	}
	
	@GetMapping(value="/{id}", produces=MediaType.APPLICATION_JSON_VALUE)
	public Candidato findById(@PathVariable("id") Long id) {
		return service.buscarPorId(id);
	}
	
	@PostMapping(consumes=MediaType.APPLICATION_JSON_VALUE, produces=MediaType.APPLICATION_JSON_VALUE)
	public Candidato create(@RequestBody Candidato candidato) {
		return service.inserir(candidato);
	}
	
	@PutMapping(consumes=MediaType.APPLICATION_JSON_VALUE, produces=MediaType.APPLICATION_JSON_VALUE)
	public Candidato update(@RequestBody Candidato candidato) {
		return service.atualizar(candidato);
	}
	
	@DeleteMapping(value="/{id}")
	public void delete(@PathVariable("id") Long id) {
		service.deletar(id);
	}
}
