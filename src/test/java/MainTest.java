import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;

import java.util.concurrent.TimeUnit;

public class MainTest {

    @Disabled
    @Test
    @Timeout(22)
    void failIfExecutionTimeExceeds22Seconds() throws Exception{
        Main.main(null);
    }

}
