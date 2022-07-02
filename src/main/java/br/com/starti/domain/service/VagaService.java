package br.com.starti.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.starti.domain.entity.Vaga;
import br.com.starti.domain.repository.VagaRepository;


@Service
public class VagaService {

		@Autowired
		VagaRepository repository;

		public void inserir(Vaga vaga){
			repository.save(vaga);
		}
}
