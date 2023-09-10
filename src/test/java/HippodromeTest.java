import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class HippodromeTest {
    @Mock
    List<Horse> horses;

    // ----------------------------------A----------------------------
    @Test
    /*
        Перевірити, що при передачі до конструктора null буде викинуто IllegalArgumentException;
     */
    public void testHippodromeConstructorWithFirstNullParamThrowException() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Hippodrome(null);
        });
    }

    @Test
    /*
        Перевірити, що при передачі до конструктора null виняток міститиме повідомлення "Horses cannot be null.";
     */
    public void testHippodromeConstructorWithFirstNullParamThrowExceptionWithMessage() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            new Hippodrome(null);
        });
        assertEquals("Horses cannot be null.", exception.getMessage());
    }

    @ExtendWith(MockitoExtension.class)
    @Test
    /*
        Перевірити, що при передачі до конструктора порожнього списку буде викинуто IllegalArgumentException;
     */
    public void testHippodromeConstructorWithEmptyListThrowException() {
        when(horses.isEmpty()).thenReturn(true);
        assertThrows(IllegalArgumentException.class, () -> {
            new Hippodrome(horses);
        });
    }

    @ExtendWith(MockitoExtension.class)
    @Test
    /*
        Перевірити, що при передачі до конструктора порожнього списку виняток міститиме повідомлення "Horses cannot be empty.";
     */
    public void testHippodromeConstructorWithEmptyListThrowExceptionWithMessage() {
        when(horses.isEmpty()).thenReturn(true);
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            new Hippodrome(horses);
        });
        assertEquals("Horses cannot be empty.", exception.getMessage());
    }

    // ----------------------------------B----------------------------
    @Test
    /*
         Перевірити, що метод повертає список, який містить ті ж об'єкти і в такій самій послідовності,
         що й список, який передався до конструктора. При створенні об'єкта Hippodrome передай до
         конструктора список із 30 різних коней;
     */
    public void testGetHorses() {
        List<Horse> originalHorsesList = new ArrayList<>();
        for (int i = 0; i < 30; i++) {
            originalHorsesList.add(new Horse("" + i, i, i));
        }
        Hippodrome hippodrome = Mockito.spy(new Hippodrome(originalHorsesList));
        assertEquals(originalHorsesList, hippodrome.getHorses());
    }

    // ----------------------------------C----------------------------
    @Test
    /*
         Перевірити, що метод викликає метод move у всіх коней. При створенні об'єкта Hippodrome
         передай до конструктора список із 50 моків коней та скористайся методом verify.
     */
    public void testMove() {
        List<Horse> originalHorsesList = new ArrayList<>();
        for (int i = 0; i < 50; i++) {
            Horse mockHorse = Mockito.mock(Horse.class);
            originalHorsesList.add(mockHorse);
        }
        Hippodrome hippodrome = Mockito.spy(new Hippodrome(originalHorsesList));
        hippodrome.move();
        for (Horse originalHorse: originalHorsesList) {
            verify(originalHorse, times(1)).move();
        }
    }

    // ----------------------------------D----------------------------
    @Test
    /*
         Перевірити, що метод повертає коня з найбільшим значенням distance
     */
    public void testGetWinner() {
        List<Horse> originalHorsesList = new ArrayList<>();
        Horse expectedHorse = null;
        for (int i = 0; i < 50; i++) {
            Horse mockHorse = Mockito.mock(Horse.class);
            if (i == 25) {
                when(mockHorse.getDistance()).thenReturn(25.0);
                expectedHorse = mockHorse;
            } else {
                when(mockHorse.getDistance()).thenReturn(1.0);
            }
            originalHorsesList.add(mockHorse);
        }
        Hippodrome hippodrome = Mockito.spy(new Hippodrome(originalHorsesList));
        assertEquals(expectedHorse, hippodrome.getWinner());
    }
}