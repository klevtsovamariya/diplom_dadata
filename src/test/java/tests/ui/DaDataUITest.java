package tests.ui;

import io.qameta.allure.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import pages.BlogPage;
import pages.HomePage;
import pages.PricesPage;

@Epic("UI тесты")
@Feature("Базовые UI тесты")
@Link(name = "Сайт", url = "https://dadata.ru/")
@Tag("ui")
class DaDataUITest extends UIBaseTest {

    private final HomePage homePage = new HomePage();
    private final PricesPage pricesPage = new PricesPage();
    private final BlogPage blogPage = new BlogPage();
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
    @DisplayName("Цены: проверить переключение вкладок подписок")
    @Description("Открываем раздел «Цены», переключаем вкладки подписок и проверяем верхний заголовок активного блока.")
    @Severity(SeverityLevel.NORMAL)
    void pricesSubscriptionTabsShouldSwitchContent() {
        homePage.openHeaderMenuItem("Цены");

        pricesPage.checkPageTitle("Цены")
                .openSubscriptionTab("Подписка с «Подсказками»")
                .checkTopSubscriptionTitle("Подписка на год")
                .openSubscriptionTab("«Стандартизация»")
                .checkTopSubscriptionTitle("«Стандартизация» — 20 копеек за запись")
                .openSubscriptionTab("«Компания по емейлу»")
                .checkTopSubscriptionTitle("«Компания по емейлу» — 7 ₽ за запись")
                .openSubscriptionTab("«Бренд по ИНН»")
                .checkTopSubscriptionTitle("«Бренд по ИНН» —7 ₽ за запись")
                .openSubscriptionTab("«Маскировщик»")
                .checkTopSubscriptionTitle("«Маскировщик» — по заявке")
                .openSubscriptionTab("«Распознавание документов»")
                .checkTopSubscriptionTitle("«Распознавание документов» — по заявке")
                .openSubscriptionTab("«Поиск дублей»")
                .checkTopSubscriptionTitle("«Поиск дублей» — 2 копейки за запись")
                .openSubscriptionTab("Корпоративный пакет")
                .checkTopSubscriptionTitle("Корпоративный пакет — 1 190 000 ₽ в год")
                .openSubscriptionTab("Коробочная версия")
                .checkTopSubscriptionTitle("Коробочная версия — от 1 490 000 ₽ за 3 года");
    }

    @Test
    @DisplayName("Блог: найти статьи по запросу")
    @Description("Открываем раздел «Блог», ищем статьи по слову «Стандартизация» и проверяем заголовок результатов и первые три статьи.")
    @Severity(SeverityLevel.NORMAL)
    void blogSearchShouldReturnStandardizationArticles() {
        homePage.openHeaderMenuItem("Блог");

        blogPage.search("Стандартизация")
                .checkSearchResultsTitle("Стандартизация")
                .checkFirstThreeArticleTitlesStartWith("Стандартизация");
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

    @ParameterizedTest(name = "Кнопка «{arguments}» открывает форму регистрации")
    @ValueSource(strings = {"Подключиться", "Присоединиться"})
    @DisplayName("Главная страница: проверить форму регистрации по главным кнопкам")
    @Description("Проверяем, что главные кнопки открывают форму регистрации с полями имени, почты и пароля, а введённые значения сохраняются в полях.")
    @Severity(SeverityLevel.CRITICAL)
    void registrationPopupShouldOpenAfterMainButtonClick(String buttonText) {
        homePage.checkRegistrationPopupAfterMainButtonClick(
                buttonText,
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
