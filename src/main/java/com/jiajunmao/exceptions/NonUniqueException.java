package com.jiajunmao.exceptions;

/**
 * This exception is thrown when an unique entry should be found but not
 */
public class NonUniqueException extends RuntimeException {

    public NonUniqueException(String str) {
        super(str);
    }
}
