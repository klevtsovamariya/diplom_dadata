package tests.ui;

import io.qameta.allure.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import pages.HomePage;

@Epic("UI тесты")
@Feature("Базовые UI тесты")
@Link(name = "Сайт", url = "https://dadata.ru/")
@Tag("ui")
class DaDataUITest extends UIBaseTest {

    private final HomePage homePage = new HomePage();
    private final TestData testData = new TestData();

    @Test
    @DisplayName("Главная страница: проверить основной заголовок и главные кнопки")
    @Description("Проверяем, что на главной странице отображается основной заголовок и две главные кнопки.")
    @Severity(SeverityLevel.BLOCKER)
    void mainPageShouldHaveHeroTitleAndButtons() {
        homePage.checkMainHeroTitle("Наводим порядок в данных")
                .checkMainHeroButtons("Подключиться", "Присоединиться");
    }

    @Test
    @DisplayName("Хедер: проверить пункты меню и иконку поддержки")
    @Description("Проверяем пункты меню в хедере и наличие иконки сообщения у пункта «Поддержка».")
    @Severity(SeverityLevel.NORMAL)
    void headerShouldHaveMenuItemsAndSupportIcon() {
        homePage.checkHeaderMenuItems(
                "Продукты",
                "API",
                "Цены",
                "Блог",
                "Клиенты",
                "Поддержка"
        );
        homePage.checkSupportIcon();
    }

    @Test
    @DisplayName("Главная страница: проверить открытие формы регистрации по кнопке входа")
    @Description("Проверяем, что кнопка «Войти» открывает форму входа с полями почты, пароля и кнопкой входа.")
    @Severity(SeverityLevel.CRITICAL)
    void registrationWindowShouldOpenAfterLoginButtonClick() {
        homePage.checkRegistrationWindowAfterLoginClick()
                .setEmail(testData.email)
                .setPassword(testData.password)
                .checkFormButton("Войти в систему");
    }

    @Test
    @DisplayName("Главная страница: проверить форму регистрации по кнопке «Подключиться»")
    @Description("Проверяем, что кнопка «Подключиться» открывает форму регистрации с полями имени, почты и пароля.")
    @Severity(SeverityLevel.CRITICAL)
    void registrationPopupShouldOpenAfterConnectButtonClick() {
        homePage.checkRegistrationPopupAfterMainButtonClick(
                "Подключиться",
                testData.name,
                testData.email,
                testData.password
        );
    }

    @Test
    @DisplayName("Главная страница: проверить форму регистрации по кнопке «Присоединиться»")
    @Description("Проверяем, что кнопка «Присоединиться» открывает форму регистрации с полями имени, почты и пароля.")
    @Severity(SeverityLevel.CRITICAL)
    void registrationPopupShouldOpenAfterJoinButtonClick() {
        homePage.checkRegistrationPopupAfterMainButtonClick(
                "Присоединиться",
                testData.name,
                testData.email,
                testData.password
        );
    }

    @Test
    @DisplayName("Меню продуктов: проверить список продуктов в выпадающем меню")
    @Description("Открываем выпадающее меню «Продукты» и проверяем список продуктовых пунктов.")
    @Severity(SeverityLevel.NORMAL)
    void productsMenuShouldContainMainProducts() {
        homePage.checkListProducts(
                "Подсказки",
                "Стандартизация",
                "Организация по ИНН",
                "Маскировщик",
                "Компания по емейлу",
                "MCP-сервер",
                "Бренд по ИНН",
                "Логотипы компаний",
                "Распознавание документов"
        );
    }
}
