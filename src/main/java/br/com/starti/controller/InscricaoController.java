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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.starti.domain.entity.Inscricao;
import br.com.starti.domain.vo.v1.EnderecoVO;
import br.com.starti.service.InscricaoService;
import io.swagger.v3.oas.annotations.Operation;


@RestController
@RequestMapping("/inscricao/v1")
public class InscricaoController {
	
	@Autowired
	InscricaoService inscricaoService;
	
	@PostMapping(consumes = { "application/json", "application/xml" }, produces = { "application/json","application/xml" })
	@Operation(summary="Cadastrar nova Inscrição")
	@ResponseStatus(HttpStatus.CREATED)
	public Inscricao create(@Valid @RequestBody Inscricao inscricao) {
		return inscricaoService.inserir(inscricao);
	}
	

	
	@GetMapping(produces = { "application/json", "application/xml" })
	@Operation(summary="Listar todas as inscrições")
	@ResponseStatus(HttpStatus.OK)
	public List<Inscricao> findAll() {
		List<Inscricao> inscricao = inscricaoService.buscarTodos();
		return inscricao;
	}
	
	@GetMapping(value="/{id}",produces = { "application/json", "application/xml" })
	@Operation(summary="Listar Vagas por Candidato")
	@ResponseStatus(HttpStatus.OK)
	public List<Inscricao> findByCandidato(@PathVariable("id")Long id) {
		List<Inscricao> inscricao = inscricaoService.buscarPorCandidato(id);
		return inscricao;
	}
	
	@DeleteMapping(value="/{id}")
	@Operation(summary="Deletar inscrição em vaga")
	@ResponseStatus(HttpStatus.OK)
	public void delete(@PathVariable("id")Long id) {
		inscricaoService.deletarPorIdVaga(id);
	}
	
}
