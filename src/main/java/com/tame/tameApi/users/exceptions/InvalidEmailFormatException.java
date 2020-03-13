package com.tame.tameApi.users.exceptions;

public class InvalidEmailFormatException extends Exception {

    public InvalidEmailFormatException() {
        super("Invalid Email format");
    }
}
