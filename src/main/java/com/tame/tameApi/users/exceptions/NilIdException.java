package com.tame.tameApi.users.exceptions;

public class NilIdException extends RuntimeException {
    NilIdException() {
        super("The provided user id does not exist");
    }
}
