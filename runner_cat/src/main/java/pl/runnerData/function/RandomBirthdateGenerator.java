package pl.runnerData.function;

import java.time.LocalDate;
import java.util.concurrent.ThreadLocalRandom;

public class RandomBirthdateGenerator {

    public static LocalDate getRandomBirthdate() {
        long startEpochDay = LocalDate.of(1950, 1, 1).toEpochDay();
        long endEpochDay = LocalDate.now().toEpochDay();

        long randomDay = ThreadLocalRandom.current().nextLong(startEpochDay, endEpochDay + 1);

        return LocalDate.ofEpochDay(randomDay);
    }
}