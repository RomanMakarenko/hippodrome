import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class HorseTest {

    // ----------------------------------A----------------------------
    @Test
    /*
        Перевірити, що при передачі до конструктора першим параметром null буде викинуто
        IllegalArgumentException. Для цього потрібно скористатися методом assertThrows;
     */
    public void testHorseConstructorWithFirstNullParamThrowException() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Horse(null, 1.0, 1.0);
        });
    }

    @Test
    /*
        Перевірити, що при передачі до конструктора першим параметром null виняток міститиме
        повідомлення "Name cannot be null.". Для цього потрібно отримати повідомлення з перехопленого
        винятку та скористатися методом assertEquals;
     */
    public void testHorseConstructorWithFirstNullParamHasExceptionMessage() {
        IllegalArgumentException exception = Assertions.assertThrows(IllegalArgumentException.class, () -> {
            new Horse(null, 1.0, 1.0);
        });
        assertEquals("Name cannot be null.", exception.getMessage());
    }

    @ParameterizedTest
    @CsvSource({
            "'', 1.0, 2.0",
            "'   ', 1.0, 2.0",
            "' ', 1.0, 2.0"
    })
    /*
        Перевірити, що при передачі до конструктора першим параметром порожнього рядка або рядка,
        що містить лише пробільні символи (пробіл, табуляція, тощо), буде викинуто
        IllegalArgumentException. Для виконання перевірки з різними варіантами пробільних
        символів потрібно зробити тест параметризованим;
     */
    public void testHorseConstructorWithInvalidFirstParamsHasException(String name, double speed, double distance) {
        assertThrows(IllegalArgumentException.class, () -> {
            new Horse(name, speed, distance);
        });
    }

    @ParameterizedTest
    @CsvSource({
            "'', 1.0, 2.0",
            "'  ', 1.0, 2.0",
            "' ', 1.0, 2.0"
    })
    /*
        Перевірити, що при передачі до конструктора першим параметром порожнього рядка або рядка,
        що містить лише пробільні символи (пробіл, табуляція, тощо), виняток міститиме
        повідомлення "Name cannot be blank.";
     */
    public void testHorseConstructorWithInvalidFirstParamsHasExceptionMessage(String name, double speed, double distance) {
        IllegalArgumentException exception = Assertions.assertThrows(IllegalArgumentException.class, () -> {
            new Horse(name, speed, distance);
        });
        assertEquals("Name cannot be blank.", exception.getMessage());
    }

    @Test
    /*
        Перевірити, що при передачі до конструктора другим параметром від'ємного числа,
        буде викинуто IllegalArgumentException;
     */
    public void testHorseConstructorWithSecondNegativeParamThrowException() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Horse("alex", -1.0, 1.0);
        });
    }

    @Test
    /*
        Перевірити, що при передачі до конструктора другим параметром від'ємного числа,
        виняток міститиме повідомлення "Speed cannot be negative.";
     */
    public void testHorseConstructorWithSecondNegativeParamHasExceptionMessage() {
        IllegalArgumentException exception = Assertions.assertThrows(IllegalArgumentException.class, () -> {
            new Horse("alex", -1.0, 1.0);
        });
        assertEquals("Speed cannot be negative.", exception.getMessage());
    }

    @Test
    /*
        Перевірити, що при передачі до конструктора третім параметром від'ємного числа,
        буде викинуто IllegalArgumentException;
     */
    public void testHorseConstructorWithThirdNegativeParamThrowException() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Horse("alex", 1.0, -1.0);
        });
    }

    @Test
    /*
        Перевірити, що при передачі до конструктора третім параметром від'ємного числа,
        виняток міститиме повідомлення "Distance cannot be negative.";
     */
    public void testHorseConstructorWithThirdNegativeParamHasExceptionMessage() {
        IllegalArgumentException exception = Assertions.assertThrows(IllegalArgumentException.class, () -> {
            new Horse("alex", 1.0, -1.0);
        });
        assertEquals("Distance cannot be negative.", exception.getMessage());
    }

    // ----------------------------------B----------------------------
    @Test
    /*
        Перевірити, що метод повертає рядок, який передавався першим параметром до конструктора;
     */
    public void testGetName() {
        String name = "alex";
        Horse mockHorse = Mockito.spy(new Horse(name, 1.0, 1.0));
        assertEquals(name, mockHorse.getName());
    }

    // ----------------------------------C----------------------------
    @Test
    /*
        Перевірити, що метод повертає число, яке передалося другим параметром до конструктора;
     */
    public void testGetSpeed() {
        double expectedSpeed = 1.0;
        Horse mockHorse = Mockito.spy(new Horse("alex", expectedSpeed, 2.0));
        assertEquals(expectedSpeed, mockHorse.getSpeed());
    }

    // ----------------------------------D----------------------------
    @Test
    /*
        Перевірити, що метод повертає число, яке передалося третім параметром конструктора;
     */
    public void testGetDistance() {
        double expectedDistance = 1.0;
        Horse mockHorse = Mockito.spy(new Horse("alex", 2.0, expectedDistance));
        assertEquals(expectedDistance, mockHorse.getDistance());
    }

    @Test
    /*
        Перевірити, чи метод повертає нуль, якщо об'єкт створено за допомогою конструктора з двома параметрами;
     */
    public void testGetDistanceWith2ParamsConstructor() {
        double expectedDistance = 0;
        Horse mockHorse = Mockito.spy(new Horse("alex", 2.0));
        assertEquals(expectedDistance, mockHorse.getDistance());
    }

    // ----------------------------------E----------------------------
    @Test
    /*
        Перевірити, що метод викликає всередині метод getRandomDouble параметрів 0.2 і 0.9.
        Для цього потрібно використовувати MockedStatic і його метод verify;
     */
    public void testMoveRequestMethod() {
        try (MockedStatic<Horse> mocked = mockStatic(Horse.class)) {
            mocked.when(() -> Horse.getRandomDouble(0.2, 0.9)).thenReturn(0.1);
            Horse mockHorse = Mockito.spy(new Horse("alex", 2.0));
            mockHorse.move();
            mocked.verify(() -> Horse.getRandomDouble(0.2, 0.9));
        }
    }

    @ParameterizedTest
    @CsvSource({
            "alex, 1.0, 1.0, 0.3, 1.3",
            "alex, 2.0, 5.0, 0.4, 5.8",
    })
    /*
        Перевірити, що метод присвоює дистанції значення, яке обчислюється за такою формулою:
        distance + speed * getRandomDouble(0.2, 0.9). Для цього потрібно замокати getRandomDouble,
        щоб він повертав певні значення, які треба встановити за допомогою параметризації тесту.
     */
    public void testMove(String name, double speed, double distance, double randValue, double result) {
        try (MockedStatic<Horse> mocked = mockStatic(Horse.class)) {
            mocked.when(() -> Horse.getRandomDouble(0.2, 0.9)).thenReturn(randValue);
            Horse mockHorse = Mockito.spy(new Horse(name, speed, distance));
            mockHorse.move();
            assertEquals(result, mockHorse.getDistance());
        }
    }
}