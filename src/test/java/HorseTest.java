import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullSource;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class HorseTest {

    @Test
    //@ParameterizedTest
    @NullSource

    void whenHorseNameNull(){
        //Check Horse constructor: name cannot be null
        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () ->
                    //throw new IllegalArgumentException("Name cannot be null.");
                    new Horse(null, 0.1));

                assertEquals("Name cannot be null.", exception.getMessage());
        System.out.println(exception.getMessage());
    }
}
