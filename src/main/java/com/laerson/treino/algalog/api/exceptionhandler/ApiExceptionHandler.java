package com.laerson.treino.algalog.api.exceptionhandler;

import java.time.LocalDateTime;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

// Anotação que trata as exceções de todos os controladores
@ControllerAdvice
public class ApiExceptionHandler extends ResponseEntityExceptionHandler {

	// é uma interface do spring usada para resolver mensagens contidas no
	// massages.properties
	@Autowired
	private MessageSource messageSource;

	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		// Cria uma variavel com uma lista de campo da classe problema
		var campos = new ArrayList<Problema.Campo>();

		// Percorre todos os erros existentes adicionando o campo com erro ao var nome e
		// mensagem padrao a var mensagem
		for (ObjectError error : ex.getBindingResult().getAllErrors()) {
			String nome = ((FieldError) error).getField();
			String mensagem = messageSource.getMessage(error, LocaleContextHolder.getLocale());

			campos.add(new Problema.Campo(nome, mensagem));
		}

		// Instancia da classe Problema
		var problema = new Problema();
		// Pega o código do status atribui para variavel status da classe problema
		problema.setStatus(status.value());
		problema.setTitulo("Um ou mais campos estão inválidos. Faça o preenchimento correto e tente novamente");
		problema.setDataHora(LocalDateTime.now());
		problema.setCampos(campos);

		return super.handleExceptionInternal(ex, problema, headers, status, request);
	}

}