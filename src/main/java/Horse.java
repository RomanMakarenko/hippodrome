import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.SimpleDateFormat;
import java.util.Date;

import static java.util.Objects.isNull;

public class Horse {
    private static final Logger logger = LoggerFactory.getLogger(Horse.class);

    private final String name;
    private final double speed;
    private double distance;

    public Horse(String name, double speed, double distance) {
        if (isNull(name)) {
            //--------------------------A----------------------
            /*
                Якщо конструктор замість імені передав null, перед прокиданням винятку треба додати в лог запис типу:
                2022-05-31 17:34:59,483 ERROR Horse: Name is null
             */
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("YYYY-MM-dd HH:mm:ss,ms");
            String dateTime = simpleDateFormat.format(new Date());
            logger.error(dateTime + " ERROR " + Horse.class.getName() + ": Name is null");
            throw new IllegalArgumentException("Name cannot be null.");
        } else if (name.isBlank()) {
            //--------------------------B----------------------
            /*
                Якщо ім'я, що передалося до конструктора порожнє, перед прокиданням винятку треба додати в лог запис типу:
                2022-05-31 17:36:44,196 ERROR Horse: Name is blank
             */
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("YYYY-MM-dd HH:mm:ss,ms");
            String dateTime = simpleDateFormat.format(new Date());
            logger.error(dateTime + " ERROR " + Horse.class.getName() + ": Name is blank");
            throw new IllegalArgumentException("Name cannot be blank.");
        }
        if (speed < 0) {
            //--------------------------C----------------------
            /*
                Якщо швидкість, що передалася до конструктора менша за нуль,
                перед прокиданням винятку треба додати в лог запис типу:
                2022-05-31 17:40:27,267 ERROR Horse: Speed is negative
             */
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("YYYY-MM-dd HH:mm:ss,ms");
            String dateTime = simpleDateFormat.format(new Date());
            logger.error(dateTime + " ERROR " + Horse.class.getName() + ": Speed is negative");
            throw new IllegalArgumentException("Speed cannot be negative.");
        }
        if (distance < 0) {
            //--------------------------D----------------------
            /*
                Якщо дистанція, що передалася до конструктора менша за нуль,
                перед прокиданням винятку треба додати в лог запис типу:
                2022-05-31 17:41:21,938 ERROR Horse: Distance is negative
             */
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("YYYY-MM-dd HH:mm:ss,ms");
            String dateTime = simpleDateFormat.format(new Date());
            logger.error(dateTime + " ERROR " + Horse.class.getName() + ": Distance is negative");
            throw new IllegalArgumentException("Distance cannot be negative.");
        }

        this.name = name;
        this.speed = speed;
        this.distance = distance;
        //--------------------------E----------------------
        /*
            Наприкінці конструктора додати в лог запис типу:
            2022-05-31 17:15:25,842 DEBUG Horse: створення Horse, ім'я [Лобстер], швидкість [2.8]
         */
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("YYYY-MM-dd HH:mm:ss,ms");
        String dateTime = simpleDateFormat.format(new Date());
        logger.debug(dateTime + " DEBUG " + Horse.class.getName() + ": створення Horse, ім'я [" + this.name + "], швидкість [" + this.distance + "]");
    }

    public Horse(String name, double speed) {
        this(name, speed, 0);
    }

    public String getName() {
        return name;
    }

    public double getSpeed() {
        return speed;
    }

    public double getDistance() {
        return distance;
    }

    public void move() {
        distance += speed * getRandomDouble(0.2, 0.9);
    }

    public static double getRandomDouble(double min, double max) {
        return (Math.random() * (max - min)) + min;
    }
}
