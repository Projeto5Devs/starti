package br.com.starti.controller;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.util.Arrays;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.starti.domain.entity.Empresa;
import br.com.starti.domain.entity.Permission;
import br.com.starti.domain.entity.PessoaFisica;
import br.com.starti.domain.vo.v1.EmpresaVO;
import br.com.starti.repository.PermissionRepository;
import br.com.starti.service.EmpresaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name="Endpoint Empresa")
@RestController
@RequestMapping("/empresas/v1")
public class EmpresaController {

	@Autowired
	EmpresaService empresaService;
	

	@Autowired
	PermissionRepository permissionRepository;
	
	@GetMapping(produces={"application/json", "application/xml"})
	@Operation(summary="Listar todas as empresas")
	@ResponseStatus(HttpStatus.OK)
	public List<EmpresaVO>findAll(){
		List<EmpresaVO>empresasVO =  empresaService.buscarTodos();
		empresasVO.stream().forEach(e -> e.add(linkTo(methodOn(EmpresaController.class).findById(e.getKey())).withSelfRel()));
		return empresasVO;
	}
	
//	@CrossOrigin({"localhost:8080"})
	@GetMapping(value="/{id}", produces={"application/json", "application/xml"})
	@Operation(summary="Procurar empresa por ID")
	@ResponseStatus(HttpStatus.OK)
	public EmpresaVO findById(@PathVariable("id")Long id) {
		EmpresaVO empresaVO =  empresaService.buscarPorId(id);
		empresaVO.add(linkTo(methodOn(EmpresaController.class).findById(id)).withSelfRel());
		return empresaVO;
	}
	

	@PostMapping(consumes= {"application/json", "application/xml"},produces={"application/json", "application/xml"})
	@Operation(summary="Cadastrar nova empresa")
	@ResponseStatus(HttpStatus.CREATED)
	public EmpresaVO create(@Valid @RequestBody EmpresaVO empresa) {
		Permission permission = permissionRepository.findByDescricao("ROLE_USER_EMPRESA");
		List<Permission> lista = Arrays.asList(permission); 
		empresa.getUserId().setPermissoes(lista);
		empresa.getUserId().setPassword(new BCryptPasswordEncoder().encode(empresa.getUserId().getPassword()));
		EmpresaVO empresaVO = empresaService.inserir(empresa);
		empresaVO.add(linkTo(methodOn(EmpresaController.class).findById(empresaVO.getKey())).withSelfRel());
		return empresaVO;
	}


	@PutMapping(consumes= {"application/json", "application/xml"},produces={"application/json", "application/xml"})
	@Operation(summary="Atualizar dados da empresa")
	@ResponseStatus(HttpStatus.OK)
	public EmpresaVO update(@Valid @RequestBody EmpresaVO empresa) {
		EmpresaVO empresaVO = empresaService.atualizar(empresa);	
		empresaVO.add(linkTo(methodOn(EmpresaController.class).findById(empresaVO.getKey())).withSelfRel());
		return empresaVO;
	}
	
	@GetMapping(value="/usuario/{id}", produces={"application/json", "application/xml"})
	@Operation(summary="Procurar pessoa por ID Usuário")
	@ResponseStatus(HttpStatus.OK)
	public Empresa findByIdUsuario(@PathVariable("id")Long id) {
		return empresaService.buscarPorUsuario(id);

	}
	
	@DeleteMapping(value="/{id}")
	@Operation(summary="Deletar empresa")
	@ResponseStatus(HttpStatus.OK)
	public void delete(@PathVariable("id")Long id) {
		empresaService.deletar(id);
	}
	
}
