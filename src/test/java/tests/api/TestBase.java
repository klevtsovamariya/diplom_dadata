package tests.api;

import api.ApiClient;
import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeAll;

import static org.junit.jupiter.api.Assumptions.assumeTrue;

public class TestBase {

    protected static final ApiClient api = new ApiClient();

    @BeforeAll
    public static void setUp() {
        RestAssured.baseURI = TestConfig.cleanerBaseUrl();
        RestAssured.basePath = "";
    }

    protected static void assumeDadataCredentialsProvided() {
        assumeTrue(!TestConfig.dadataApiToken().isBlank(),
                "Для запуска API-тестов нужен системный параметр dadata.api.token");
        assumeTrue(!TestConfig.dadataApiSecret().isBlank(),
                "Для запуска API-тестов нужен системный параметр dadata.api.secret");
    }

    protected static void assumeDadataTokenProvided() {
        assumeTrue(!TestConfig.dadataApiToken().isBlank(),
                "Для запуска Suggestions API-тестов нужен системный параметр dadata.api.token");
    }
}
