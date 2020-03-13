package com.tame.tameApi.users.exceptions;

public class TooYoungException extends Exception {
    public TooYoungException() {
        super("User's age and min age are to close");
    }
}
