package com.agenda.agenda.domain.exception;


public enum ErrorEnum implements ExceptionResponseInterface {
	
	/**Exceções genéricas*/
	B001("Não foi possível realizar o cadastro!"),
	B002("ID não informado!"),
	B003("Registro não encontrado!"),
	B004("Não foi possível atualizar o registro!"),
	B005("Não foi possível deletar o registro!"),

	USU001("ID informado é diferente do usuário logado!");
	
	private String error;
	
    ErrorEnum(String error) {
    	this.error = error;
    }

	@Override
	public String getErrorCode() {
		return this.name();
	}

	@Override
	public String getMessage() {
		return this.error;
	}

}
