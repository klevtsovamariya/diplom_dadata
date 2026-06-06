package tests.api;

import io.qameta.allure.Description;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import models.cleaner.NameCleanResponseModel;
import models.cleaner.NameRecordResponseModel;
import models.cleaner.NameResponseModel;
import models.cleaner.PhoneCleanResponseModel;
import models.cleaner.PhoneResponseModel;
import models.cleaner.RecordBodyModel;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import java.util.List;

import static io.qameta.allure.Allure.step;
import static org.assertj.core.api.Assertions.assertThat;

@Feature("DaData Cleaner API")
@Tag("api")
@DisplayName("DaData Cleaner API")
class CleanerApiTests extends TestBase {

    @Test
    @DisplayName("ФИО приводится к стандартному виду")
    @Description("POST /api/v1/clean/NAME нормализует ФИО и возвращает разобранные части имени.")
    @Severity(SeverityLevel.CRITICAL)
    void cleanNameShouldNormalizeFullNameTest() {
        assumeDadataCredentialsProvided();

        String source = "иванов иван иванович";

        NameCleanResponseModel response = api.cleaner.cleanName(source);

        step("Проверить результат очистки ФИО", () -> {
            assertThat(response).hasSize(1);

            NameResponseModel name = response.get(0);
            assertThat(name.source()).isEqualTo(source);
            assertThat(name.result()).isEqualTo("Иванов Иван Иванович");
            assertThat(name.surname()).isEqualTo("Иванов");
            assertThat(name.name()).isEqualTo("Иван");
            assertThat(name.patronymic()).isEqualTo("Иванович");
            assertThat(name.qc()).isEqualTo(0);
        });
    }

    @Test
    @DisplayName("Запрос без авторизации не выполняется")
    @Description("POST /api/v1/clean/NAME без Authorization и X-Secret возвращает ошибку авторизации.")
    @Severity(SeverityLevel.CRITICAL)
    void cleanNameWithoutAuthorizationShouldBeRejectedTest() {
        String source = "иванов иван иванович";

        api.cleaner.cleanNameWithoutAuthorization(source);
    }

    @Test
    @DisplayName("Телефон приводится к международному формату")
    @Description("POST /api/v1/clean/PHONE приводит телефон к единому формату и выделяет код страны.")
    @Severity(SeverityLevel.NORMAL)
    void cleanPhoneShouldNormalizeRussianPhoneTest() {
        assumeDadataCredentialsProvided();

        String source = "8 912 345-67-89";

        PhoneCleanResponseModel response = api.cleaner.cleanPhone(source);

        step("Проверить результат очистки телефона", () -> {
            assertThat(response).hasSize(1);

            PhoneResponseModel phone = response.get(0);
            assertThat(phone.source()).isEqualTo(source);
            assertThat(phone.phone()).startsWith("+7");
            assertThat(phone.country_code()).isEqualTo("7");
            assertThat(phone.number()).isNotNull();
            assertThat(phone.qc()).isEqualTo(0);
        });
    }

    @Test
    @DisplayName("Запись с ФИО возвращается структурированно")
    @Description("POST /api/v1/clean очищает строку данных по переданной структуре NAME.")
    @Severity(SeverityLevel.CRITICAL)
    void cleanRecordShouldReturnStructuredDataTest() {
        assumeDadataCredentialsProvided();

        String source = "иванов иван иванович";
        RecordBodyModel requestBody = new RecordBodyModel(
                List.of("NAME"),
                List.of(List.of(source))
        );

        NameRecordResponseModel response = api.cleaner.cleanNameRecord(requestBody);

        step("Проверить структуру ответа", () -> {
            assertThat(response.structure()).containsExactly("NAME");
            assertThat(response.data()).hasSize(1);
        });

        step("Проверить очищенное ФИО в первой строке", () -> {
            NameResponseModel name = response.data().get(0).get(0);

            assertThat(name.source()).isEqualTo(source);
            assertThat(name.result()).isEqualTo("Иванов Иван Иванович");
        });
    }
}
