package com.tame.tameApi.users.exceptions;

public class ToYoungException extends Exception {
    public ToYoungException() {
        super("User's age and min age are to close");
    }
}
