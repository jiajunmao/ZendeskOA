import com.jiajunmao.processors.Nice;
import com.jiajunmao.utils.Util;
import org.junit.jupiter.api.Test;

import java.text.SimpleDateFormat;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

public class MiscTests {

    @Test
    void isIntTest() {
        String realInt = "100";
        String realDouble = "120.04";
        String realString = "string";

        assertTrue(Util.isInt(realInt));
        assertFalse(Util.isInt(realDouble));
        assertFalse(Util.isInt(realString));
    }

    @Test
    void niceStringTest() {
        String longStr = "123456789";
        String nice3str = "123...";

        assertEquals(nice3str, Nice.niceString(longStr, 3));
        assertEquals(longStr, Nice.niceString(longStr, 15));
    }

    @Test
    void breakStringTest() {
        String longStr = "Lorem ipsum dolor sit amet";
        String break3String = "Lorem\nipsum\ndolor\nsit\namet";

        assertEquals(break3String, Nice.breakString(longStr, 3));
        assertEquals(longStr, Nice.breakString(longStr, 40));
    }

    @Test
    void nicePriorityTest() {
        assertEquals("N", Nice.nicePriority("normal"));
        assertEquals("U", Nice.nicePriority("urgent"));
    }

    @Test
    void niceDateTest() {
        SimpleDateFormat outputFormat = new SimpleDateFormat("MM-dd \n HH:mm");
        SimpleDateFormat inputFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");
        Date date = new Date();
        String dateStr = outputFormat.format(date);

        assertEquals(dateStr, Nice.niceDate(inputFormat.format(date)));
    }
}
