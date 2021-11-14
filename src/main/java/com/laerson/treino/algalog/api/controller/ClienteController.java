package com.laerson.treino.algalog.api.controller;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.laerson.treino.algalog.domain.model.Cliente;
import com.laerson.treino.algalog.domain.repository.ClienteRepository;

@RequestMapping("/clientes")
@RestController
public class ClienteController {
	
	/*
	 * Interface do Jarkata Persistence que é usada para fazer operações nas entidades de consulta, exclusão, edição.
	 * Para ele ficar disponível é necessário injetalo através da anotação PersistenceContext
	 * */
	@PersistenceContext
	private EntityManager maneger;
	
	/*
	 * Consulta realizada através do JPQL, retornando uma lista de clientes
	 * */
	
	@GetMapping(value = "/teste")
	public List<Cliente> listarClientes() {
		return maneger.createQuery("from Cliente", Cliente.class).getResultList();
	}
	
	/*
	 * Criando uma instancia de clienteRepository
	 * */	
	@Autowired
	private ClienteRepository clienteRepository;
	
	public List<Cliente> listar() {
		return clienteRepository.findAll();
	}
	

	

}
