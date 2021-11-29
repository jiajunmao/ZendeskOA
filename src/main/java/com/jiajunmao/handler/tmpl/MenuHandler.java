package com.jiajunmao.handler.tmpl;

import java.util.*;

public abstract class MenuHandler extends Handler {

    private final Map<String, Handler> handlers;

    public MenuHandler() {
        handlers = new HashMap<>();
    }

    public MenuHandler(String description) {
        super(description);
        handlers = new HashMap<>();
    }

    public void registerHandler(String key, Handler handler) {
        handlers.put(key, handler);
    }

    public abstract boolean execute(int argc, String[] argv);

    public Handler getHandler(String key) {
        return handlers.get(key);
    }

    public boolean hasHandler(String key) {
        return handlers.containsKey(key);
    }

    public String getText(String format) {
        StringBuilder sb = new StringBuilder();

        List<String> keyList = new LinkedList<>(handlers.keySet());
        Collections.sort(keyList);
        for (String str : keyList) {
            String formatted = format.replace("%k", str).replace("%d", handlers.get(str).getDescription());
            sb.append(formatted);
            sb.append("\n");
        }

        return sb.toString();
    }

    public String getDefaultText() {
        return getText("%k - %d");
    }
}