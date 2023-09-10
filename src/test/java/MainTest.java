import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;

class MainTest {
    // ----------------------------------A----------------------------
    @Test
    @Disabled()
    @Timeout(22)
    /*
        Перевірити, що метод виконується не довше 22 секунд.
        Для цього скористайся анотацією Timeout. Після написання цього тесту,
        вимкни його (скористайся анотацією Disabled). Таким чином він не займатиме
        час під час запуску всіх тестів, а за необхідності його можна буде запустити вручну.
     */
    public void testMain() {
        String[] args = new String[]{};
        try {
            Main.main(args);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}