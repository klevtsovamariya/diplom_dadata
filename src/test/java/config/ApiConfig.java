package config;

import org.aeonbits.owner.Config;

@Config.Sources({
        "file:${user.home}/dadata-secret.properties",
        "classpath:application.properties"
})
public interface ApiConfig extends Config {

    @Key("dadata.cleaner.baseUrl")
    @DefaultValue("https://cleaner.dadata.ru")
    String cleanerBaseUrl();

    @Key("dadata.suggestions.baseUrl")
    @DefaultValue("https://suggestions.dadata.ru/suggestions")
    String suggestionsBaseUrl();

    @Key("dadata.api.token")
    String dadataApiToken();

    @Key("dadata.api.secret")
    String dadataApiSecret();
}
