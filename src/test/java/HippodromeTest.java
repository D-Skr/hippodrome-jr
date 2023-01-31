import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.provider.NullSource;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class HippodromeTest {

    private List<Horse> horses = new ArrayList<>();

    //2.a
    @Test
    @NullSource
    void constructorHippodromeNull(){
        //Check Hippodrome constructor: list cannot be null
        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () ->
                        //throw new IllegalArgumentException("Horses cannot be null.");
                        new Hippodrome(null));
        //Check the message if list is null
        assertEquals("Horses cannot be null.", exception.getMessage());
    }

    @Test
    void constructorHippodromeEmpty(){
        //Check Hippodrome constructor: list cannot be empty
        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () ->
                        //throw new IllegalArgumentException("Horses cannot be empty");
                        new Hippodrome(horses));
        //Check the message if list is null
        assertEquals("Horses cannot be empty.", exception.getMessage());
    }

    //2.b
    @Test
    void getHorsesReturnsList(){
        List<Horse> list = new ArrayList<>();
        for (int i =0; i < 30; i++){
            list.add(new Horse("horse #" + i, i+1, i+2));
        }
        assertArrayEquals(list.toArray(), new Hippodrome(list).getHorses().toArray());
    }

    //2.c
    @Test
    void hippodromeMovesHorses(){
        List<Horse> mockedList = new ArrayList<>();
        for (int i =0; i < 50; i++){
            mockedList.add(mock(Horse.class));
        }
        Hippodrome hippodrome = new Hippodrome(mockedList);
        hippodrome.move();
        mockedList.forEach(horse -> verify(horse).move());
    }

    @Test
    void getCorrectWinner(){
        List<Horse> list = new ArrayList<>();
        for (int i =0; i < 11; i++){
            list.add(new Horse("horse #" + i, i+1, i+2));
        }
        assertEquals(list.get(10), new Hippodrome(list).getWinner());
    }


}
