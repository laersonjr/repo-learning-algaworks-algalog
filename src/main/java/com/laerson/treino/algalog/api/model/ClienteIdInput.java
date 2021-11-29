package com.laerson.treino.algalog.api.model;

import javax.validation.constraints.NotNull;

public class ClienteIdInput {
	
	@NotNull
	private Long id;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	
}
