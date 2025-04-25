package br.com.fillms.projeto_java_ia.exceptions;

public class UserNotFound extends RuntimeException {
    
    public UserNotFound() {
        super("User not found");
    }
    
}
