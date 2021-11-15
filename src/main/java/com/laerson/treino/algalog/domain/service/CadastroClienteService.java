package com.laerson.treino.algalog.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.laerson.treino.algalog.domain.exception.NegocioException;
import com.laerson.treino.algalog.domain.model.Cliente;
import com.laerson.treino.algalog.domain.repository.ClienteRepository;

@Service
public class CadastroClienteService {

	@Autowired
	private ClienteRepository clienteRepository;
	
	public Cliente salvar(Cliente cliente) {
		Cliente clienteExiste = clienteRepository.findByEmail(cliente.getEmail());
		
		// Metodo verifica se o cliente é diferente de nulo e se o clienteExiste for diferente do cliente do parâmetro 
		if(clienteExiste != null && !clienteExiste.equals(cliente)) {
			throw new NegocioException("Já existe um cliente cadastrado com esse e-mail.");
		}
		
		return clienteRepository.save(cliente);
	}
	
	public void excluir(Long clienteId) {
		clienteRepository.deleteById(clienteId);
	}
	
}
