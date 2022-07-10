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

import br.com.starti.domain.vo.v1.EmpresaVO;
import br.com.starti.service.EmpresaService;


@RestController
@RequestMapping("/empresas/v1")
public class EmpresaController {

	@Autowired
	EmpresaService empresaService;
	
	@GetMapping(produces={"application/json", "application/xml"})
	@ResponseStatus(HttpStatus.OK)
	public List<EmpresaVO>findAll(){
		return empresaService.buscarTodos();
	}
	
	@GetMapping(value="/{id}", produces={"application/json", "application/xml"})
	@ResponseStatus(HttpStatus.OK)
	public EmpresaVO findById(@PathVariable("id")Long id) {
		return empresaService.buscarPorId(id);
	}
	
	@PostMapping(consumes= {"application/json", "application/xml"},produces={"application/json", "application/xml"})
	@ResponseStatus(HttpStatus.CREATED)
	public EmpresaVO create(@Valid @RequestBody EmpresaVO empresa) {
		return empresaService.inserir(empresa);
	}

	@PutMapping(consumes= {"application/json", "application/xml"},produces={"application/json", "application/xml"})
	@ResponseStatus(HttpStatus.OK)
	public EmpresaVO update(@Valid @RequestBody EmpresaVO empresa) {
		return empresaService.atualizar(empresa);	
	}
	
	@DeleteMapping(value="/{id}")
	@ResponseStatus(HttpStatus.OK)
	public void delete(@PathVariable("id")Long id) {
		empresaService.deletar(id);
	}
	
}
