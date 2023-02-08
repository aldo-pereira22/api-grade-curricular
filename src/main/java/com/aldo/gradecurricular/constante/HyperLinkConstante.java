package com.aldo.gradecurricular.constante;

import lombok.Getter;

@Getter
public enum HyperLinkConstante {
	
	ATUALIZAR("UPDATE"),
	EXCLUIR("DELETE"),
	LISTAR("GET_ALL"),
	CONSTULTAR("GET");
	
	private final String valor;
	
	private HyperLinkConstante(String valor) {
		this.valor = valor;
	}
}
