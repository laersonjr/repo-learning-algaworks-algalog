package com.laerson.treino.algalog.api.controller;

import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.laerson.treino.algalog.api.model.ComentarioInput;
import com.laerson.treino.algalog.api.model.ComentarioModel;
import com.laerson.treino.algalog.domain.exception.EntidadeNaoEncontradaException;
import com.laerson.treino.algalog.domain.model.Comentario;
import com.laerson.treino.algalog.domain.model.OrdemServico;
import com.laerson.treino.algalog.domain.repository.OrdemServicoRepository;
import com.laerson.treino.algalog.domain.service.GestaoOrdemServicoService;

@RestController
@RequestMapping("/ordem-servico/{ordemServicoId}/comentarios")
public class ComentarioController {
	
	@Autowired
	private GestaoOrdemServicoService gestaoOrdemServico;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	private OrdemServicoRepository ordemServicoRepository;
	
	@GetMapping
	public List<ComentarioModel> listar(@PathVariable Long ordemServicoId) {
		OrdemServico ordemServico = ordemServicoRepository.findById(ordemServicoId)
				.orElseThrow(() -> new EntidadeNaoEncontradaException("Ordem de serviço não encontrada"));
		
		return toCollectionModel(ordemServico.getComentarios());
	}
	
	private List<ComentarioModel> toCollectionModel(List<Comentario> comentarios) {
		return comentarios.stream().map(comentario -> toModel(comentario))
				.collect(Collectors.toList());
	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public ComentarioModel adicionar(@PathVariable Long ordemServicoId,
			@Valid @RequestBody ComentarioInput comentarioInput) {
		
		Comentario comentario = gestaoOrdemServico.adicionarComentario(ordemServicoId, comentarioInput.getDescricao());
		
		return toModel(comentario);
	}
	
	private ComentarioModel toModel(Comentario comentario) {
		return modelMapper.map(comentario, ComentarioModel.class);
	}
	
}
