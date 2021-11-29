package com.jiajunmao.handler.executors;

import com.jiajunmao.handler.tmpl.Handler;

public class ExitHandler extends Handler {

    public ExitHandler(String description) {
        super(description);
    }

    @Override
    public boolean execute(int argc, String[] argv) {
        return false;
    }
}
