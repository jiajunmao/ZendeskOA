package com.jiajunmao.handler.menus;

import com.jiajunmao.handler.executors.ListAllHandler;
import com.jiajunmao.handler.tmpl.MenuHandler;
import com.jiajunmao.utils.Util;

import java.util.Scanner;

public class ListAllMenu extends MenuHandler {

    public ListAllMenu(String description) {
        super(description);
    }

    @Override
    public boolean execute(int argc, String[] argv) {
        boolean firstTime = true;
        while (true) {
            if (firstTime) new ListAllHandler().execute(2, new String[]{"1", "firstTime"});
            firstTime = false;

            System.out.println("------------------------------");
            System.out.print(getDefaultText());
            System.out.println("------------------------------");

            Scanner scanner = new Scanner(System.in);
            String input = scanner.nextLine();

            if (hasHandler(input)) {
                if (!getHandler(input).execute(argc, argv))
                    break;
            } else if (Util.isInt(input)) {
                if (!getHandler("#").execute(1, new String[]{input}))
                    break;
            } else {
                System.out.println("Input option not recognized!");
            }
        }

        return true;
    }
}
