package com.mypetshop.api.controller.exception;

public class InvalidEmailException extends Exception {
	
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String email;

    public static InvalidEmailException createWith(String email) {
        return new InvalidEmailException(email);
    }

    private InvalidEmailException(String email) {
        this.email = email;
    }

    @Override
    public String getMessage() {
        return "Invalid email.";
    }
}