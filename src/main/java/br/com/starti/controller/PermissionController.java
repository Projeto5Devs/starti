package br.com.starti.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;


import br.com.starti.domain.entity.Permission;
import br.com.starti.service.PermissionService;
import io.swagger.v3.oas.annotations.Operation;

@RestController
@RequestMapping("/permissao/v1")
public class PermissionController {

	@Autowired
	PermissionService service;
	
	@GetMapping(value="/descricao/{descricao}",produces = { "application/json", "application/xml" })
	@Operation(summary="Listar por Descricao")
	@ResponseStatus(HttpStatus.OK)
	public Permission findByDescricao(@PathVariable("descricao")String descricao) {
		Permission permissao = service.buscarPorDescricao(descricao);
		return permissao;
	}
	
}
