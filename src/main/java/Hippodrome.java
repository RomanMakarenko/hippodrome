import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

import static java.util.Objects.isNull;

public class Hippodrome {
    private static final Logger logger = LoggerFactory.getLogger(Hippodrome.class);
    private final List<Horse> horses;

    public Hippodrome(List<Horse> horses) {
        if (isNull(horses)) {
            //--------------------------A----------------------
            /*
                Якщо до конструктора передався null, то перед прокиданням виключення треба додати в лог запис типу:
                2022-05-31 17:29:30,029 ERROR Hippodrome: Horses list is null
             */
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("YYYY-MM-dd HH:mm:ss,ms");
            String dateTime = simpleDateFormat.format(new Date());
            logger.error(dateTime + " ERROR " + Hippodrome.class.getName() + ": Horses list is null");
            throw new IllegalArgumentException("Horses cannot be null.");
        } else if (horses.isEmpty()) {
            //--------------------------B----------------------
            /*
                Якщо до конструктора передався порожній список, то перед прокиданням виключення треба додати в лог запис типу:
                2022-05-31 17:30:41,074 ERROR Hippodrome: Horses list is empty
             */
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("YYYY-MM-dd HH:mm:ss,ms");
            String dateTime = simpleDateFormat.format(new Date());
            logger.error(dateTime + " ERROR " + Hippodrome.class.getName() + ": Horses list is empty");
            throw new IllegalArgumentException("Horses cannot be empty.");
        }

        this.horses = horses;
        //--------------------------C----------------------
        /*
            Наприкінці конструктора додати в лог запис типу:
            2022-05-31 17:05:26,152 DEBUG Hippodrome: створення Hippodrome, коней [7]
         */
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("YYYY-MM-dd HH:mm:ss,ms");
        String dateTime = simpleDateFormat.format(new Date());
        logger.debug(dateTime + " DEBUG " + Hippodrome.class.getName() + ": створення Hippodrome, коней [" + this.horses.size() + "]");
    }

    public List<Horse> getHorses() {
        return Collections.unmodifiableList(horses);
    }

    public void move() {
        horses.forEach(Horse::move);
    }

    public Horse getWinner() {
        return horses.stream()
                .max(Comparator.comparing(Horse::getDistance))
                .get();
    }
}
