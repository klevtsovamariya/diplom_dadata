package api;

import config.ApiConfig;
import io.qameta.allure.Step;
import models.suggestions.SuggestRequestModel;
import models.suggestions.SuggestResponseAddressModel;
import models.suggestions.SuggestResponseEmailModel;

import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.aeonbits.owner.ConfigFactory.create;
import static specs.suggestions.SuggestionsSpec.successfulSuggestResponseSpec;
import static specs.suggestions.SuggestionsSpec.suggestionsRequestSpec;

public class SuggestionsApiClient {

    private static final String SUGGEST_ADDRESS_ENDPOINT = "/api/4_1/rs/suggest/address";
    private static final String SUGGEST_EMAIL_ENDPOINT = "/api/4_1/rs/suggest/email";
    private static final ApiConfig config = create(ApiConfig.class, System.getProperties());

    @Step("Получить подсказки по адресу")
    public SuggestResponseAddressModel suggestAddress(String query, int count) {
        return given(suggestionsRequestSpec)
                .headers(authHeaders())
                .body(new SuggestRequestModel(query, count))
                .when()
                .post(SUGGEST_ADDRESS_ENDPOINT)
                .then()
                .spec(successfulSuggestResponseSpec)
                .extract()
                .as(SuggestResponseAddressModel.class);
    }

    @Step("Получить подсказки по email")
    public SuggestResponseEmailModel suggestEmail(String query, int count) {
        return given(suggestionsRequestSpec)
                .headers(authHeaders())
                .body(new SuggestRequestModel(query, count))
                .when()
                .post(SUGGEST_EMAIL_ENDPOINT)
                .then()
                .spec(successfulSuggestResponseSpec)
                .extract()
                .as(SuggestResponseEmailModel.class);
    }

    private Map<String, String> authHeaders() {
        return Map.of("Authorization", "Token " + config.dadataApiToken());
    }
}
