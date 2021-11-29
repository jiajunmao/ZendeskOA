package com.jiajunmao.handler.menus;

import com.jiajunmao.handler.tmpl.MenuHandler;

import java.util.Scanner;

public class RootMenu extends MenuHandler {

    @Override
    public boolean execute(int argc, String[] argv) {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("------------------------------");
            System.out.print(getDefaultText());
            System.out.println("------------------------------");

            String input = scanner.nextLine();

            if (hasHandler(input)) {
                // Not passing any argument to the submenus
                if (!getHandler(input).execute(1, new String[]{input}))
                    break;
            } else {
                System.out.println("Input option not recognized!");
            }
        }

        // This is a menu, therefore we always return true
        return true;
    }
}
