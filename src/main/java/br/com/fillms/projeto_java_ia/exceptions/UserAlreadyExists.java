package br.com.fillms.projeto_java_ia.exceptions;

public class UserAlreadyExists extends RuntimeException {
    public UserAlreadyExists() {
        super("User already exists");
    }

   
    
}
