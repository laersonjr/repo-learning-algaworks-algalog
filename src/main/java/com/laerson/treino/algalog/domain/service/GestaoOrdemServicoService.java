package com.laerson.treino.algalog.domain.service;

import java.time.OffsetDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.laerson.treino.algalog.domain.exception.EntidadeNaoEncontradaException;
import com.laerson.treino.algalog.domain.exception.NegocioException;
import com.laerson.treino.algalog.domain.model.Cliente;
import com.laerson.treino.algalog.domain.model.Comentario;
import com.laerson.treino.algalog.domain.model.OrdemServico;
import com.laerson.treino.algalog.domain.model.StatusOrdemServico;
import com.laerson.treino.algalog.domain.repository.ClienteRepository;
import com.laerson.treino.algalog.domain.repository.ComentarioRepository;
import com.laerson.treino.algalog.domain.repository.OrdemServicoRepository;

@Service
public class GestaoOrdemServicoService {

	@Autowired
	private OrdemServicoRepository ordemServicoRepository;

	@Autowired
	private ClienteRepository clienteRepository;

	@Autowired
	private ComentarioRepository comentarioRepository;

	// Metodo para criar ordem de serviço, inicia com status aberta e com data de
	// abertura de abertura de acordo de quando foi criada
	public OrdemServico criar(OrdemServico ordemServico) {
		// Realiza uma busca de cliente por ID, se não encontrar lança uma exceção
		Cliente cliente = clienteRepository.findById(ordemServico.getCliente().getId())
				.orElseThrow(() -> new NegocioException("Cliente não encontrado"));

		ordemServico.setCliente(cliente);
		ordemServico.setStatus(StatusOrdemServico.ABERTA);
		ordemServico.setDataAbertura(OffsetDateTime.now());

		return ordemServicoRepository.save(ordemServico);
	}

	public Comentario adicionarComentario(Long ordemServicoId, String descricao) {
		OrdemServico ordemServico = buscar(ordemServicoId);

		Comentario comentario = new Comentario();
		comentario.setDataEnvio(OffsetDateTime.now());
		comentario.setDescricao(descricao);
		comentario.setOrdemServico(ordemServico);

		return comentarioRepository.save(comentario);

	}

	private OrdemServico buscar(Long ordemServicoId) {
		return ordemServicoRepository.findById(ordemServicoId)
				.orElseThrow(() -> new EntidadeNaoEncontradaException("Ordem de serviço não encontrada."));
	}
	
	public void finalizar(Long ordemServicoId) {
		OrdemServico ordemServico = buscar(ordemServicoId);
		
		ordemServico.finalizar();
		
		ordemServicoRepository.save(ordemServico);
	}

}
