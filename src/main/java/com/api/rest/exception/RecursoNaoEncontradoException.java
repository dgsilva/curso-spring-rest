package com.api.rest.exception;

public class RecursoNaoEncontradoException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public RecursoNaoEncontradoException() {
		super("Usuario não encontrado");
	}

}
