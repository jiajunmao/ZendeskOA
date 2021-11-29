import com.jiajunmao.processors.Parser;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ParserTest {

    Parser parser;

    @BeforeEach
    void setup() {
        parser = new Parser(".parserTest.yml");
    }

    @Test
    void parserGetString() {
        assertEquals("haha", parser.getString("1.str.content"));
    }
}
