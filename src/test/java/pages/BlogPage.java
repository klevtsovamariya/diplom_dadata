package pages;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import static com.codeborne.selenide.CollectionCondition.sizeGreaterThanOrEqual;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;
import static org.openqa.selenium.Keys.ENTER;

public class BlogPage {

    private final SelenideElement searchInput = $("input.orig[name='phrase']");
    private final SelenideElement pageContent = $("body");
    private final ElementsCollection searchResultLinks = $$(".search-post__link");

    @Step("Найти в блоге статьи по запросу «{query}»")
    public BlogPage search(String query) {
        searchInput.shouldBe(visible).setValue(query).press(ENTER);

        return this;
    }

    @Step("Проверить заголовок результатов поиска по запросу «{query}»")
    public BlogPage checkSearchResultsTitle(String query) {
        pageContent.shouldHave(text("Результаты по запросу: " + query));

        return this;
    }

    @Step("Проверить, что первые три статьи начинаются со слова «{expectedWord}»")
    public BlogPage checkFirstThreeArticleTitlesStartWith(String expectedWord) {
        searchResultLinks.shouldHave(sizeGreaterThanOrEqual(3));

        searchResultLinks.get(0).shouldBe(visible).shouldHave(text("«" + expectedWord));
        searchResultLinks.get(1).shouldBe(visible).shouldHave(text("«" + expectedWord));
        searchResultLinks.get(2).shouldBe(visible).shouldHave(text("«" + expectedWord));

        return this;
    }
}
