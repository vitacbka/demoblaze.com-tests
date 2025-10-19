package helpers;

import com.github.javafaker.Faker;

public class FakeDataGeneratorHelper {

    Faker faker = new Faker();

    public String generateRandomUsername() {
        return faker.name().username();
    }

    public String generateRandomPassword() {
        return faker.internet().password(8, 16);
    }

    public String generateRandomLongPassword(int minLength) {
        return faker.internet().password(minLength, minLength + 10);
    }

    public String generateRandomVeryShortPassword(int maxLength) {
        return faker.internet().password(1, maxLength);
    }
}
