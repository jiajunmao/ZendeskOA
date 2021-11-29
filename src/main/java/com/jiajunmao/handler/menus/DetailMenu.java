package com.jiajunmao.handler.menus;

import com.jiajunmao.handler.tmpl.MenuHandler;
import com.jiajunmao.utils.Util;

import java.util.Scanner;

public class DetailMenu extends MenuHandler {

    public DetailMenu(String str) {
        super(str);
    }

    @Override
    public boolean execute(int argc, String[] argv) {
        while (true) {
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

        // This is a menu, therefore we always return true
        return true;
    }
}
