package tests.api;

import io.qameta.allure.Description;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import models.suggestions.SuggestResponseAddressModel;
import models.suggestions.SuggestResponseEmailModel;
import models.suggestions.SuggestionAddressModel;
import models.suggestions.SuggestionEmailModel;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static io.qameta.allure.Allure.step;
import static org.assertj.core.api.Assertions.assertThat;

@Feature("DaData Suggestions API")
@Tag("api")
@DisplayName("DaData Suggestions API")
class SuggestionsApiTests extends TestBase {

    @Test
    @DisplayName("Подсказки адресов возвращают подходящий адрес")
    @Description("POST /api/4_1/rs/suggest/address возвращает подсказки по части адреса.")
    @Severity(SeverityLevel.CRITICAL)
    void suggestAddressShouldReturnRelevantAddressTest() {
        assumeDadataTokenProvided();

        SuggestResponseAddressModel response = api.suggestions.suggestAddress("москва сухонская 11", 3);

        step("Проверить первую подсказку адреса", () -> {
            assertThat(response.suggestions()).isNotEmpty();

            SuggestionAddressModel address = response.suggestions().get(0);
            assertThat(address.value()).containsIgnoringCase("Москва");
            assertThat(address.data().city()).isEqualTo("Москва");
            assertThat(address.data().fias_id()).isNotBlank();
        });
    }

    @Test
    @DisplayName("Подсказки email возвращают доменные варианты")
    @Description("POST /api/4_1/rs/suggest/email возвращает варианты email по неполному адресу.")
    @Severity(SeverityLevel.NORMAL)
    void suggestEmailShouldReturnDomainVariantsTest() {
        assumeDadataTokenProvided();

        SuggestResponseEmailModel response = api.suggestions.suggestEmail("test.user@gm", 3);

        step("Проверить первую подсказку email", () -> {
            assertThat(response.suggestions()).isNotEmpty();

            SuggestionEmailModel email = response.suggestions().get(0);
            assertThat(email.value()).startsWith("test.user@");
            assertThat(email.value()).contains(".");
        });
    }
}
