package com.lms.exceptions;

public class PasswordNotMatchException extends Exception implements Message{

	private static final long serialVersionUID = 1L;
	
	public PasswordNotMatchException(){
		super(PASSWORD_NOT_MATCH);
	}
	
	public PasswordNotMatchException(String message){
		super(message);
	}

}
