package com.laerson.treino.algalog.core;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

// Configuração do ModelMapper
@Configuration
public class ModelMapperConfig {
	
	@Bean
	public ModelMapper modelMapper() {
		return new ModelMapper();
	}

}
