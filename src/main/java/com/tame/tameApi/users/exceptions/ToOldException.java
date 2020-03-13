package com.tame.tameApi.users.exceptions;

public class ToOldException extends Exception {
    public ToOldException() {
        super("User's age and max age are to close");
    }
}
