package com.laerson.treino.algalog.api.controller;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
	
	// Consulta realizada através do JPQL, retornando uma lista de clientes
	@GetMapping(value = "/teste")
	public List<Cliente> listarClientes() {
		return maneger.createQuery("from Cliente", Cliente.class).getResultList();
	}
	
	// Criando uma instancia de clienteRepository
	@Autowired
	private ClienteRepository clienteRepository;
	
	// Metodo que retonar toda a lista de clientes
	@GetMapping
	public List<Cliente> listar() {
		return clienteRepository.findAll();
	}
	
	// Meotodo que retonar cliente a partir de um ID, se não encontrar retorna status 404 not found
	@GetMapping("/{clienteId}")
	public ResponseEntity<Cliente> buscar(@PathVariable Long clienteId) {
		Optional<Cliente> cliente = clienteRepository.findById(clienteId);
		
		return cliente.isPresent() ? ResponseEntity.ok(cliente.get()) : ResponseEntity.notFound().build();
	}
	

}
