package com.lms.exceptions;

public class StudentIDTakenException extends Exception implements Message{

	private static final long serialVersionUID = 1L;
	
	public StudentIDTakenException(){
		super(STUDENT_ID_TAKEN);
	}
	
	public StudentIDTakenException(String message){
		super(message);
	}
}
