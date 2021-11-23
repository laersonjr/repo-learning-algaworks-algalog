package com.laerson.treino.algalog.domain.service;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.laerson.treino.algalog.domain.model.OrdemServico;
import com.laerson.treino.algalog.domain.model.StatusOrdemServico;
import com.laerson.treino.algalog.domain.repository.OrdemServicoRepository;

@Service
public class GestaoOrdemServicoService {

	@Autowired
	private OrdemServicoRepository ordemServicoRepository;

	// Metodo para criar ordem de serviço, inicia com status aberta e com data de
	// abertura de abertura de acordo de quando foi criada
	public OrdemServico criar(OrdemServico ordemServico) {
		ordemServico.setStatus(StatusOrdemServico.ABERTA);
		ordemServico.setDataAbertura(LocalDateTime.now());

		return ordemServicoRepository.save(ordemServico);
	}

}
