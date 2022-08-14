package br.com.starti.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.starti.domain.entity.ChaveCompostaInscricao;
import br.com.starti.domain.entity.Inscricao;

import br.com.starti.repository.InscricaoRepository;

@Service
public class InscricaoService {

	
	@Autowired
	InscricaoRepository repository;
	
	public Inscricao inserir(Inscricao inscricao) {
		ChaveCompostaInscricao chave = new ChaveCompostaInscricao();
		chave.setIdPessoaFisica(inscricao.getPessoafisica().getIdPessoaFisica());
		chave.setIdVaga(inscricao.getVaga().getId());
		inscricao.setIdInscricao(chave);
		return repository.save(inscricao);
		
	}
	
	
	public List<Inscricao> buscarTodos() {
		return repository.findAll();
	}
	
	
	
}
