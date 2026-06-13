package pages;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class PricesPage {

    private final SelenideElement pageTitle = $(".motto__title");
    private final SelenideElement activeSubscriptionTab = $(".demo-form-menu-button.active");
    private final ElementsCollection sectionTitles = $$("h2.module__title");

    @Step("Проверить заголовок страницы цен")
    public PricesPage checkPageTitle(String expectedTitle) {
        pageTitle.shouldBe(visible).shouldHave(exactText(expectedTitle));

        return this;
    }

    @Step("Открыть вкладку подписки «{tabName}»")
    public PricesPage openSubscriptionTab(String tabName) {
        $(byText(tabName)).scrollTo().shouldBe(visible).click();
        activeSubscriptionTab.shouldHave(text(tabName));

        return this;
    }

    @Step("Проверить верхний заголовок подписки: «{expectedTitleText}»")
    public PricesPage checkTopSubscriptionTitle(String expectedTitleText) {
        sectionTitles.findBy(visible).shouldHave(text(expectedTitleText));

        return this;
    }
}
