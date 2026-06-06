package api;

import config.ApiConfig;
import io.qameta.allure.Step;
import models.cleaner.AsIsCleanResponseModel;
import models.cleaner.EmailCleanResponseModel;
import models.cleaner.NameCleanResponseModel;
import models.cleaner.NameRecordResponseModel;
import models.cleaner.PhoneCleanResponseModel;
import models.cleaner.RecordBodyModel;

import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.aeonbits.owner.ConfigFactory.create;
import static specs.cleaner.CleanerSpec.cleanerRequestSpec;
import static specs.cleaner.CleanerSpec.successfulCleanArrayResponseSpec;
import static specs.cleaner.CleanerSpec.successfulCleanRecordResponseSpec;
import static specs.cleaner.CleanerSpec.unauthorizedCleanResponseSpec;

public class CleanerApiClient {

    private static final String CLEAN_SIMPLE_ENDPOINT = "/api/v1/clean/{type}";
    private static final String CLEAN_RECORD_ENDPOINT = "/api/v1/clean";
    private static final ApiConfig config = create(ApiConfig.class, System.getProperties());

    @Step("Отправить ФИО на очистку")
    public NameCleanResponseModel cleanName(String source) {
        return given(cleanerRequestSpec)
                .headers(authHeaders())
                .body(List.of(source))
                .when()
                .post(CLEAN_SIMPLE_ENDPOINT, "NAME")
                .then()
                .spec(successfulCleanArrayResponseSpec)
                .extract()
                .as(NameCleanResponseModel.class);
    }

    @Step("Отправить ФИО на очистку без авторизации")
    public void cleanNameWithoutAuthorization(String source) {
        given(cleanerRequestSpec)
                .body(List.of(source))
                .when()
                .post(CLEAN_SIMPLE_ENDPOINT, "NAME")
                .then()
                .spec(unauthorizedCleanResponseSpec);
    }

    @Step("Отправить телефон на очистку")
    public PhoneCleanResponseModel cleanPhone(String source) {
        return given(cleanerRequestSpec)
                .headers(authHeaders())
                .body(List.of(source))
                .when()
                .post(CLEAN_SIMPLE_ENDPOINT, "PHONE")
                .then()
                .spec(successfulCleanArrayResponseSpec)
                .extract()
                .as(PhoneCleanResponseModel.class);
    }

    @Step("Отправить email на очистку")
    public EmailCleanResponseModel cleanEmail(String source) {
        return given(cleanerRequestSpec)
                .headers(authHeaders())
                .body(List.of(source))
                .when()
                .post(CLEAN_SIMPLE_ENDPOINT, "EMAIL")
                .then()
                .spec(successfulCleanArrayResponseSpec)
                .extract()
                .as(EmailCleanResponseModel.class);
    }

    @Step("Отправить значение без стандартизации")
    public AsIsCleanResponseModel cleanAsIs(String source) {
        return given(cleanerRequestSpec)
                .headers(authHeaders())
                .body(List.of(source))
                .when()
                .post(CLEAN_SIMPLE_ENDPOINT, "AS_IS")
                .then()
                .spec(successfulCleanArrayResponseSpec)
                .extract()
                .as(AsIsCleanResponseModel.class);
    }

    @Step("Отправить запись с ФИО на очистку")
    public NameRecordResponseModel cleanNameRecord(RecordBodyModel body) {
        return given(cleanerRequestSpec)
                .headers(authHeaders())
                .body(body)
                .when()
                .post(CLEAN_RECORD_ENDPOINT)
                .then()
                .spec(successfulCleanRecordResponseSpec)
                .extract()
                .as(NameRecordResponseModel.class);
    }

    private Map<String, String> authHeaders() {
        return Map.of(
                "Authorization", "Token " + config.dadataApiToken(),
                "X-Secret", config.dadataApiSecret()
        );
    }
}
