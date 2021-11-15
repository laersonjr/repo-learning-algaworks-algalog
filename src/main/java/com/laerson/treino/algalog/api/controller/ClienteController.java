package com.laerson.treino.algalog.api.controller;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.laerson.treino.algalog.domain.model.Cliente;
import com.laerson.treino.algalog.domain.repository.ClienteRepository;

@RequestMapping("/clientes")
@RestController
public class ClienteController {

	/*
	 * Interface do Jarkata Persistence que é usada para fazer operações nas
	 * entidades de consulta, exclusão, edição. Para ele ficar disponível é
	 * necessário injetalo através da anotação PersistenceContext
	 */
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

	// Metodo que retonar cliente a partir de um ID, se não encontrar retorna status
	// 404 not found
	@GetMapping("/{clienteId}")
	public ResponseEntity<Cliente> buscar(@PathVariable Long clienteId) {
		Optional<Cliente> cliente = clienteRepository.findById(clienteId);

		return cliente.isPresent() ? ResponseEntity.ok(cliente.get()) : ResponseEntity.notFound().build();
	}

	// Metodo para salvar cliente, retorna status 201 created
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Cliente adicionar(@Valid @RequestBody Cliente cliente) {
		return clienteRepository.save(cliente);
	}

	// Metodo que atualiza cliente caso exista, se não existir retorna not Found
	@PutMapping("/{clienteId}")
	public ResponseEntity<Cliente> atualizar(@Valid @PathVariable Long clienteId, @RequestBody Cliente cliente) {

		if (!clienteRepository.existsById(clienteId)) {
			return ResponseEntity.notFound().build();
		}

		cliente.setId(clienteId);
		cliente = clienteRepository.save(cliente);

		return ResponseEntity.ok(cliente);
	}

	// Metodo que deleta cliente por id
	@DeleteMapping("/{clienteId}")
	public ResponseEntity<Void> remover(@PathVariable Long clienteId) {

		if (!clienteRepository.existsById(clienteId)) {
			return ResponseEntity.notFound().build();
		}

		clienteRepository.deleteById(clienteId);

		return ResponseEntity.noContent().build();
	}

}
