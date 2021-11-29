package com.jiajunmao.exceptions;

/**
 * This is an encapsulation of TimeoutException, ExecutionException, and InterruptedException
 */
public class WaitException extends RuntimeException {

    public WaitException(Exception e) {
        super(e);
    }

    public WaitException(String str) {
        super(str);
    }
}
