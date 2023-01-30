import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullSource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class HorseTest {

    private String horseName = "Klyacha";
    private Double horseSpeed = 1.1;
    @Test
    //@ParameterizedTest
    @NullSource

    void constructorHorseNameNull(){
        //Check Horse constructor: name cannot be null
        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () ->
                    //throw new IllegalArgumentException("Name cannot be null.");
                    new Horse(null, horseSpeed));

                //Check the message if name is null
                assertEquals("Name cannot be null.", exception.getMessage());
        //System.out.println(exception.getMessage());
    }

    //Check for empty strings and only-spaces name
    @ParameterizedTest
    @ValueSource(strings = {""," ","\t","\s"})
        void constructorHorseNameEmptyString(String emptyName) {
            IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () ->
                        //throw new IllegalArgumentException("Name cannot be null.");
                        new Horse(emptyName, horseSpeed));

        //Check the message if name is blank
        assertEquals("Name cannot be blank.", exception.getMessage());
    }

    //Check for negative double in speed
    @ParameterizedTest
    @ValueSource(doubles = {-500,-1.5,-0.1})
    void constructorHorseSpeedNegativeNumber(Double doubles) {
        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () ->
                        //throw new IllegalArgumentException("Speed cannot be negative.");
                        new Horse(horseName, doubles));

        //Check the message if speed is negative
        assertEquals("Speed cannot be negative.", exception.getMessage());
    }

    //Check for negative number in distance
    @ParameterizedTest
    @ValueSource(doubles = {-500,-1.5,-0.1})
    void constructorHorseDistanceNegativeNumber(Double doubles) {
        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () ->
                        //throw new IllegalArgumentException("Distance cannot be negative.");
                        new Horse(horseName, horseSpeed, doubles));

        //Check the message if distance is negative
        assertEquals("Distance cannot be negative.", exception.getMessage());
    }
}
