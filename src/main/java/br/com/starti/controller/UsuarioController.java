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

import br.com.starti.domain.vo.v1.UsuarioVO;
import br.com.starti.service.UsuarioService;
import io.swagger.v3.oas.annotations.Operation;

@RestController
@RequestMapping("/usuario/v1")
public class UsuarioController {

	@Autowired
	UsuarioService usuarioService;
	
	@GetMapping(produces = { "application/json", "application/xml" })
	@Operation(summary="Listar todos os usuários")
	@ResponseStatus(HttpStatus.OK)
	public List<UsuarioVO> findAll() {
		List<UsuarioVO> usuarioVO = usuarioService.buscarTodos();
		usuarioVO.stream()
				.forEach(p -> p.add(linkTo(methodOn(UsuarioController.class).findById(p.getKey())).withSelfRel()));
		return usuarioVO;
	}
	
	@GetMapping(value = "/{id}", produces = { "application/json", "application/xml" })
	@Operation(summary="Procurar usuário por ID")
	@ResponseStatus(HttpStatus.OK)
	public UsuarioVO findById(@PathVariable("id") Long id) {
		UsuarioVO usuarioVO = usuarioService.buscarPorId(id);
		usuarioVO.add(linkTo(methodOn(UsuarioController.class).findById(id)).withSelfRel());
		return usuarioVO;
	}

	@PostMapping(consumes = { "application/json", "application/xml" }, produces = { "application/json","application/xml" })
	@Operation(summary="Cadastrar novo usuário")
	@ResponseStatus(HttpStatus.CREATED)
	public UsuarioVO create(@Valid @RequestBody UsuarioVO usuario) {
		UsuarioVO usuarioVO = usuarioService.inserir(usuario);
		usuarioVO.add(linkTo(methodOn(UsuarioController.class).findById(usuarioVO.getKey())).withSelfRel());
		return usuarioVO;
	}

	@PutMapping(consumes = { "application/json", "application/xml" }, produces = { "application/json","application/xml" })
	@Operation(summary="Atualizar usuário")
	@ResponseStatus(HttpStatus.OK)
	public UsuarioVO update(@Valid @RequestBody UsuarioVO usuario) {
		UsuarioVO usuarioVO = usuarioService.atualizar(usuario);
		usuarioVO.add(linkTo(methodOn(UsuarioController.class).findById(usuarioVO.getKey())).withSelfRel());
		return usuarioVO;
	}

	@DeleteMapping(value = "/{id}")
	@Operation(summary="Deletar usuário")
	@ResponseStatus(HttpStatus.OK)
	public void delete(@PathVariable("id") Long id) {
		usuarioService.deletar(id);
	}
	
}
