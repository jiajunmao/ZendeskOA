import com.jiajunmao.exceptions.NonUniqueException;
import com.jiajunmao.exceptions.WaitException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ExceptionTests {

    @Test
    void exceptionMsgTest() {
        try {
            throw new NonUniqueException("message");
        } catch (NonUniqueException e) {
            assertEquals(e.getMessage(), "message");
        }

        try {
            throw new WaitException("message");
        } catch (WaitException e) {
            assertEquals(e.getMessage(), "message");
        }
    }
}
