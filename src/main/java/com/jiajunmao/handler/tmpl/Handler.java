package com.jiajunmao.handler.tmpl;

public abstract class Handler implements Executor {

    private String description;

    public Handler() {
    }

    public Handler(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
