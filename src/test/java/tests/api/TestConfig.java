package tests.api;

public class TestConfig {

    private static final String DEFAULT_CLEANER_BASE_URL = "https://cleaner.dadata.ru";
    private static final String DEFAULT_SUGGESTIONS_BASE_URL = "https://suggestions.dadata.ru/suggestions";

    public static String cleanerBaseUrl() {
        return property("dadata.cleaner.baseUrl", DEFAULT_CLEANER_BASE_URL);
    }

    public static String suggestionsBaseUrl() {
        return property("dadata.suggestions.baseUrl", DEFAULT_SUGGESTIONS_BASE_URL);
    }

    public static String dadataApiToken() {
        return property("dadata.api.token", "");
    }

    public static String dadataApiSecret() {
        return property("dadata.api.secret", "");
    }

    private static String property(String name, String defaultValue) {
        String value = System.getProperty(name, "");
        return value.isBlank() ? defaultValue : value;
    }
}
