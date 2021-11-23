package com.laerson.treino.algalog.domain.service;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.laerson.treino.algalog.domain.exception.NegocioException;
import com.laerson.treino.algalog.domain.model.Cliente;
import com.laerson.treino.algalog.domain.model.OrdemServico;
import com.laerson.treino.algalog.domain.model.StatusOrdemServico;
import com.laerson.treino.algalog.domain.repository.ClienteRepository;
import com.laerson.treino.algalog.domain.repository.OrdemServicoRepository;

@Service
public class GestaoOrdemServicoService {

	@Autowired
	private OrdemServicoRepository ordemServicoRepository;
	
	@Autowired
	private ClienteRepository clienteRepository;

	// Metodo para criar ordem de serviço, inicia com status aberta e com data de
	// abertura de abertura de acordo de quando foi criada
	public OrdemServico criar(OrdemServico ordemServico) {
		// Realiza uma busca de cliente por ID, se não encontrar lança uma exceção
		Cliente cliente = clienteRepository.findById(ordemServico.getCliente().getId())
				.orElseThrow(() -> new NegocioException("Cliente não encontrado"));
		
		ordemServico.setCliente(cliente);
		ordemServico.setStatus(StatusOrdemServico.ABERTA);
		ordemServico.setDataAbertura(LocalDateTime.now());

		return ordemServicoRepository.save(ordemServico);
	}

}
