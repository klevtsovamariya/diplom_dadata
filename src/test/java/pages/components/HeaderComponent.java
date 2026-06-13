package pages.components;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class HeaderComponent {

    private final ElementsCollection pageHeader = $$(".page__heading");
    private final ElementsCollection productItems = $$(".menu-dropdown__item");
    private final SelenideElement menuWrapper = $(".menu-desktop");
    private final SelenideElement supportIcon = menuWrapper.$$(".menu__link").findBy(text("Поддержка")).$(".fa.fa-comment");
    private final SelenideElement productsDropdownButton = menuWrapper.$("[data-test='menu-dropdown']");

    @Step("Проверить пункты меню в хедере")
    public HeaderComponent verifyHeaderMenuItems(String... expectedTexts) {
        menuWrapper.shouldBe(visible);
        for (String expectedText : expectedTexts) {
            menuWrapper.shouldHave(text(expectedText));
        }

        return this;
    }

    @Step("Открыть раздел «{itemText}» из хедера")
    public HeaderComponent openHeaderMenuItem(String itemText) {
        menuWrapper.$$(byText(itemText)).findBy(visible).click();

        return this;
    }

    @Step("Проверить иконку поддержки")
    public HeaderComponent checkSupportIcon() {
        supportIcon.shouldBe(visible);

        return this;
    }

    @Step("Открыть выпадающее меню продуктов")
    public HeaderComponent openProductsDropdown() {
        productsDropdownButton.shouldBe(visible).click();

        return this;
    }

    @Step("Проверить список продуктов в выпадающем меню")
    public HeaderComponent verifyProductsDropdownItems(String... expectedTexts) {
        for (String expectedText : expectedTexts) {
            productItems.findBy(text(expectedText)).shouldBe(visible);
        }

        return this;
    }

}
