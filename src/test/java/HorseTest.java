import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.MockedStatic;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mockStatic;

public class HorseTest {

    private String horseName = "Old Klyacha";
    private Double horseSpeed = 1.1;
    private Double horseDistance = 11.1;


    //1.a
    @Test
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

    //1.b
    @Test
    //Check getName method returns horse name
    void getNameTest(){
        Horse horse = new Horse(horseName, horseSpeed);
        assertEquals(horseName, horse.getName());
    }

    //1.c
    @Test
    //Check getSpeed method returns horse speed
    void getSpeedTest(){
        Horse horse = new Horse(horseName, horseSpeed);
        assertEquals(horseSpeed, horse.getSpeed());
    }

    //1.d
    @Test
    //Check getDistance method returns horse distance
    void getDistanceTest(){
        Horse horse1 = new Horse(horseName, horseSpeed);
        assertEquals(0, horse1.getDistance());
        Horse horse2 = new Horse(horseName, horseSpeed,horseDistance);
        assertEquals(horseDistance, horse2.getDistance());
    }

    //1.e
    //Check method move
    @Test
    //@ValueSource(doubles = {0.2,0.9}
    void useGetRandomDouble() {
        try (MockedStatic<Horse> mockedHorse = mockStatic(Horse.class)) {
            new Horse(horseName,horseSpeed).move();
            mockedHorse.verify(() -> Horse.getRandomDouble(0.2, 0.9));
        }
    }

    //Check distance + speed * getRandomDouble(0.2, 0.9)
    @ParameterizedTest
    @ValueSource(doubles = {0.2,0.9})
    void checkDistanceFormula(Double result){
        try (MockedStatic<Horse> mockedHorse = mockStatic(Horse.class)) {
            mockedHorse.when(() -> Horse.getRandomDouble(0.2,0.9)).thenReturn(result);
            double expected = horseDistance + horseSpeed * result;
            Horse horse = new Horse(horseName,horseSpeed,horseDistance);
            horse.move();
            assertEquals(expected, horse.getDistance());
        }
    }


}
