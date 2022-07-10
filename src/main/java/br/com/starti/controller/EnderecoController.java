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

import br.com.starti.domain.entity.Endereco;
import br.com.starti.domain.vo.v1.EnderecoVO;
import br.com.starti.service.EnderecoService;



@RestController
@RequestMapping("/endereco/v1")
public class EnderecoController {

		@Autowired
		EnderecoService enderecoService;
	
		@GetMapping(produces={"application/json", "application/xml"})
		@ResponseStatus(HttpStatus.OK)
		public List<Endereco>findAll(){
			return enderecoService.buscarTodos();
		}
		
		@GetMapping(value="/{id}", produces={"application/json", "application/xml"})
		@ResponseStatus(HttpStatus.OK)
		public Endereco findById(@PathVariable("id")Long id) {
			return enderecoService.buscarPorId(id);
		}
		
		@PostMapping(consumes= {"application/json", "application/xml"},produces={"application/json", "application/xml"})
		@ResponseStatus(HttpStatus.CREATED)
		public EnderecoVO create(@Valid @RequestBody EnderecoVO endereco) {
			return enderecoService.inserir(endereco);
		}
	
		@PutMapping(consumes= {"application/json", "application/xml"},produces={"application/json", "application/xml"})
		@ResponseStatus(HttpStatus.OK)
		public EnderecoVO update(@Valid @RequestBody EnderecoVO endereco) {
			return enderecoService.atualizar(endereco);	
		}
		
		@DeleteMapping(value="/{id}")
		@ResponseStatus(HttpStatus.OK)
		public void delete(@PathVariable("id")Long id) {
			enderecoService.deletar(id);
		}
}
