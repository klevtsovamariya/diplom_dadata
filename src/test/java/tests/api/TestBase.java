package tests.api;

import api.ApiClient;
import config.ApiConfig;
import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeAll;

import static org.aeonbits.owner.ConfigFactory.create;
import static org.junit.jupiter.api.Assumptions.assumeTrue;

public class TestBase {

    protected static final ApiClient api = new ApiClient();
    private static final ApiConfig config = create(ApiConfig.class, System.getProperties());

    @BeforeAll
    public static void setUp() {
        RestAssured.baseURI = config.cleanerBaseUrl();
        RestAssured.basePath = "";
    }

    // перед api-тестом проверяю, что ключи заданы — в dadata-secret.properties или через -D
    // если пусто, тест не падает, а пропускается — так проще гонять ui отдельно, без ключей, так как у меня ограничено количество бесплатных вызовов api у dadata
    protected static void assumeDadataCredentialsProvided() {
        assumeTrue(!config.dadataApiToken().isBlank(),
                "Для запуска API-тестов нужен dadata.api.token в application.conf или системном параметре");
        assumeTrue(!config.dadataApiSecret().isBlank(),
                "Для запуска API-тестов нужен dadata.api.secret в application.conf или системном параметре");
    }

    // для suggestions хватает одного токена, секрет не нужен
    protected static void assumeDadataTokenProvided() {
        assumeTrue(!config.dadataApiToken().isBlank(),
                "Для запуска Suggestions API-тестов нужен dadata.api.token в application.conf или системном параметре");
    }
}
