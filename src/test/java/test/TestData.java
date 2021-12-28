package test;

import com.github.javafaker.Faker;
import config.CredentialsConfig;
import org.aeonbits.owner.ConfigFactory;

public class TestData {

    public static CredentialsConfig credentials =
            ConfigFactory.create(CredentialsConfig.class);

    static Faker faker = new Faker();
    public static String title = faker.name().title();
    public static String slug = faker.internet().slug();
    public static String markdown = faker.number().digits(15);
    public static String text = faker.backToTheFuture().toString();
}
