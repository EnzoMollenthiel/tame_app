package com.tame.tameApi.users.exceptions;

public class InvalidEmailFormatException extends Exception {

    InvalidEmailFormatException() {
        super("Invalid Email format");
    }
}
