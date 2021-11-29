package com.jiajunmao.processors;

import org.yaml.snakeyaml.Yaml;

import java.io.InputStream;
import java.util.Map;

public class Parser {

    private final Map<String, Object> root;

    public Parser(String filePath) {
        Yaml yaml = new Yaml();
        InputStream is = this.getClass().getClassLoader().getResourceAsStream(filePath);
        this.root = (Map<String, Object>) yaml.load(is);
    }

    public String getString(String path_) {
        try {
            String[] path = path_.split("\\.");
            Map<String, Object> currRoot = root;
            for (int i = 0; i < path.length - 1; i++) {
                currRoot = (Map<String, Object>) currRoot.get(path[i]);
            }

            String result = currRoot.get(path[path.length - 1]).toString();
            return result;
        } catch (NullPointerException e) {
            return null;
        }
    }
}
