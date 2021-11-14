package com.laerson.treino.algalog.domain.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.laerson.treino.algalog.domain.model.Cliente;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Long>{
	
	/*Pesquisa clientes pelo nome, tem que ser identico*/
	List<Cliente> findByNome(String nome);
	/*Pesquisa cliente onde o parametro recebido contem no nome*/
	List<Cliente> findByNomeContaining(String nome);
	

}
