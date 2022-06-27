package utilities;

import com.github.javafaker.Faker;

import java.util.Random;

public class DataFaker {

    private static DataFaker dataFaker;

    private String email, fullName, mobileNumber;

    private Faker faker;

    private String specialCharsRegex = "[-+.^:,']";

    private DataFaker() {
        faker = new Faker();
    }

    public static DataFaker getDataFaker() {
        if (dataFaker == null) {
            return new DataFaker();
        }
        return dataFaker;
    }

    public static int generateRandomNumber() {
        Random random = new Random();
        return random.nextInt(99999);
    }

    public String generateEmail() {
        return email = "son" + generateRandomNumber() + "@gmail.com";
    }
    public String generateFullName() {
        return fullName = faker.name().fullName().replaceAll(specialCharsRegex,"");
    }

}
