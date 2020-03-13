package com.tame.tameApi.users.exceptions;

public class TooOldException extends Exception {
    public TooOldException() {
        super("User's age and max age are to close");
    }
}
