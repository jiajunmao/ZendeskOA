package com.jiajunmao;

import com.jiajunmao.handler.executors.DetailHandler;
import com.jiajunmao.handler.executors.ExitHandler;
import com.jiajunmao.handler.executors.ListAllHandler;
import com.jiajunmao.handler.menus.DetailMenu;
import com.jiajunmao.handler.menus.ListAllMenu;
import com.jiajunmao.handler.menus.RootMenu;
import com.jiajunmao.processors.Parser;

public class CommandLine {

    public static void main(String[] args) {
        RootMenu root = new RootMenu();
        Parser menuParser = new Parser("menu.yml");

        DetailMenu detailMenu = new DetailMenu(menuParser.getString("root.2.description"));
        detailMenu.registerHandler("#", new DetailHandler(menuParser.getString("detail.1.description")));
        detailMenu.registerHandler("return", new ExitHandler(menuParser.getString("universal.return")));

        ListAllMenu listAllMenu = new ListAllMenu(menuParser.getString("root.1.description"));
        listAllMenu.registerHandler("#", new ListAllHandler(menuParser.getString("listAll.1.description")));
        listAllMenu.registerHandler("return", new ExitHandler(menuParser.getString("universal.return")));

        root.registerHandler("1", listAllMenu);
        root.registerHandler("2", detailMenu);
        root.registerHandler("exit", new ExitHandler(menuParser.getString("root.3.description")));

        root.execute(0, null);
    }
}
