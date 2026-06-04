package pages;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import pages.components.HeaderComponent;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;

public class HomePage {
    HeaderComponent headerComponent = new HeaderComponent();

    private SelenideElement mainHeroTitle = $(".motto__title");
    private SelenideElement connectButton = $(byText("Подключиться"));
    private SelenideElement joinButton = $(byText("Присоединиться"));
    private SelenideElement registrationForm = $("#registration-form");
    private SelenideElement loginForm = $("#login-form");
    private SelenideElement loginButton = $("[data-test='login-button']");
    private SelenideElement registrationNameInput = registrationForm.$("input[name='full_name']");
    private SelenideElement registrationEmailInput = registrationForm.$("input[name='email']");
    private SelenideElement registrationPasswordInput = registrationForm.$("input[name='password']");
    private SelenideElement loginEmailInput = loginForm.$("input[name='email']");
    private SelenideElement loginPasswordInput = loginForm.$("input[name='password']");
    private SelenideElement loginFormButton = loginForm.$(".form-button");


    @Step("Проверить главный заголовок: «{text}»")
    public HomePage checkMainHeroTitle(String text) {
        mainHeroTitle.shouldBe(visible).shouldHave(exactText(text));
        return this;
    }

    @Step("Проверить главные кнопки: «{connectText}» и «{joinText}»")
    public HomePage checkMainHeroButtons(String connectText, String joinText) {
        connectButton.shouldBe(visible).shouldHave(exactText(connectText));
        joinButton.shouldBe(visible).shouldHave(exactText(joinText));

        return this;
    }

    @Step("Проверить, что кнопка «{buttonText}» открывает форму регистрации с полями имени, почты и пароля и заполнить поля")
    public HomePage checkRegistrationPopupAfterMainButtonClick(
            String buttonText,
            String name,
            String email,
            String password
    ) {
        $(byText(buttonText)).scrollTo().shouldBe(visible).click();
        registrationForm.shouldBe(visible);
        registrationNameInput.shouldBe(visible).setValue(name);
        registrationEmailInput.shouldBe(visible).setValue(email);
        registrationPasswordInput.shouldBe(visible).setValue(password);


        return this;
    }

    @Step("Проверить форму входа при клике на кнопку «Войти»")
    public HomePage checkRegistrationWindowAfterLoginClick() {
        loginButton.shouldBe(visible, enabled).click();
        loginForm.shouldBe(visible);

        return this;
    }

    @Step("Ввести email")
    public HomePage setEmail(String email) {
        loginEmailInput.shouldBe(visible).setValue(email);

        return this;
    }

    @Step("Ввести пароль")
    public HomePage setPassword(String password) {
        loginPasswordInput.shouldBe(visible).setValue(password);

        return this;
    }

    @Step("Проверка кнопки логина {text}")
    public HomePage checkFormButton(String text) {
        loginFormButton.shouldBe(visible).shouldHave(exactText(text));

        return this;
    }

    @Step("Проверить список продуктов в выпадающем меню")
    public HomePage checkListProducts(String... expectedTexts) {
        headerComponent.openProductsDropdown();
        headerComponent.verifyProductsDropdownItems(expectedTexts);

        return this;
    }

    @Step("Проверить пункты меню в хедере")
    public HomePage checkHeaderMenuItems(String... expectedTexts) {
        headerComponent.verifyHeaderMenuItems(expectedTexts);

        return this;
    }

    @Step("Проверить иконку поддержки")
    public HomePage checkSupportIcon() {
        headerComponent.checkSupportIcon();

        return this;
    }
}
