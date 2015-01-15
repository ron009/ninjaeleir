package com.lms.exceptions;

public interface Message {
	String PASSWORD_NOT_MATCH = "Password did not match. Try Again.";
	String INVALID_CAPTCHA = "Captcha Validation Failed. Try Again";
	String STUDENT_ID_TAKEN = "The student ID is already taken. Choose a new one";
	String NOT_REGISTERED ="NOT REGISTERED";
}
