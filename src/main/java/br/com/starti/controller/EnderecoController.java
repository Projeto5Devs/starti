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

import br.com.starti.domain.entity.Endereco;
import br.com.starti.domain.service.EnderecoService;



@RestController
@RequestMapping("/endereco")
public class EnderecoController {

		@Autowired
		EnderecoService enderecoService;
	
		@GetMapping(produces=MediaType.APPLICATION_JSON_VALUE)
		public List<Endereco>findAll(){
			return enderecoService.buscarTodos();
		}
		
		@GetMapping(value="/{id}", produces=MediaType.APPLICATION_JSON_VALUE)
		public Endereco findById(@PathVariable("id")Integer id) {
			return enderecoService.buscarPorId(id);
		}
		
		@PostMapping(consumes=MediaType.APPLICATION_JSON_VALUE, produces=MediaType.APPLICATION_JSON_VALUE)
		public Endereco create(@RequestBody Endereco endereco) {
			return enderecoService.inserir(endereco);
		}
	
		@PutMapping(consumes=MediaType.APPLICATION_JSON_VALUE, produces=MediaType.APPLICATION_JSON_VALUE)
		public Endereco update(@RequestBody Endereco endereco) {
			return enderecoService.atualizar(endereco);	
		}
		
		@DeleteMapping(value="/{id}")
		public void delete(@PathVariable("id")Integer id) {
			enderecoService.deletar(id);
		}
}
