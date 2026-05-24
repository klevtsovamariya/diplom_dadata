package tests.ui;

import net.datafaker.Faker;

import java.util.Locale;

public class TestData {
    private Faker faker = new Faker();
    private Faker fakerRu = new Faker(new Locale("ru"));

    public String email = faker.internet().emailAddress();
    public String name = fakerRu.name().fullName();
    public String password = faker.number().digits(10);
}