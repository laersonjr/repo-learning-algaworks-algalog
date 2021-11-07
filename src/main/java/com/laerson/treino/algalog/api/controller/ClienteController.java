package com.laerson.treino.algalog.api.controller;

import java.util.Arrays;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.laerson.treino.algalog.domain.model.Cliente;

@RequestMapping("/clientes")
@RestController
public class ClienteController {
	
	@GetMapping
	public List<Cliente> listar() {
		Cliente cliente1 = new Cliente();
		cliente1.setId(1L);
		cliente1.setNome("Laerson");
		cliente1.setEmail("laerson@email.com");
		cliente1.setTelefone("meu telefone");
		
		Cliente cliente2 = new Cliente();
		cliente2.setId(2L);
		cliente2.setNome("CastroS");
		cliente2.setEmail("castro@email.com");
		cliente2.setTelefone("meu telefone");
		
		return Arrays.asList(cliente1,cliente2);
	}

}
