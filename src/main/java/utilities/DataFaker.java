package utilities;

import com.github.javafaker.Faker;

public class DataFaker {

    private static DataFaker dataFaker;

    private String email;

    private Faker faker;

    private DataFaker() {
        faker = new Faker();
    }

    public static DataFaker getDataFaker() {
        if (dataFaker == null) {
            return new DataFaker();
        }
        return dataFaker;
    }

    public String generateEmail() {
        return email = faker.internet().emailAddress();
    }
}
