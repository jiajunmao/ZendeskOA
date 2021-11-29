import com.jiajunmao.handler.executors.ExitHandler;
import com.jiajunmao.handler.menus.RootMenu;
import org.json.JSONObject;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;

import java.io.*;
import java.nio.charset.StandardCharsets;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class RootMenuTest {

    @Test
    void rootmenu_defaultText() {
        RootMenu rootMenu = new RootMenu();
        rootMenu.registerHandler("key", new ExitHandler("description"));

        assertEquals(rootMenu.getDefaultText(), "key - description\n");
    }

    @Test
    void rootmenu_defaultTextPrint() {
        RootMenu rootMenu = new RootMenu();
        rootMenu.registerHandler("exit", new ExitHandler("description"));

        ByteArrayInputStream in = new ByteArrayInputStream("exit".getBytes());
        System.setIn(in);

        ByteArrayOutputStream output = new ByteArrayOutputStream();
        System.setOut(new PrintStream(output));

        rootMenu.execute(0, null);

        assertEquals(output.toString(),
                "------------------------------\n" +
                "exit - description\n" +
                "------------------------------\n");
    }

    @Test
    void rootmenu_invalidInput() {
        RootMenu rootMenu = new RootMenu();
        rootMenu.registerHandler("exit", new ExitHandler("description"));

        String input = "invalid" + System.getProperty("line.separator") + "exit" + System.getProperty("line.separator");
        ByteArrayInputStream in = new ByteArrayInputStream(input.getBytes(StandardCharsets.UTF_8));
        System.setIn(in);

        ByteArrayOutputStream output = new ByteArrayOutputStream();
        System.setOut(new PrintStream(output));

        rootMenu.execute(0, null);
        assertEquals(output.toString(),
                "------------------------------\n" +
                        "exit - description\n" +
                        "------------------------------\n" +
                        "Input option not recognized!\n" +
                        "------------------------------\n" +
                        "exit - description\n" +
                        "------------------------------\n");
    }

    @Test
    @Timeout(5)
    void rootmenu_exit() {
        RootMenu rootMenu = new RootMenu();
        rootMenu.registerHandler("exit", new ExitHandler("description"));
        ByteArrayInputStream in = new ByteArrayInputStream("exit".getBytes());
        System.setIn(in);
        rootMenu.execute(0, null);

        assertTrue(true);
    }
}
