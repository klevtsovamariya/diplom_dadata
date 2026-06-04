package models.suggestions;

public record SuggestionAddressModel(
        String value,
        String unrestricted_value,
        AddressSuggestionDataModel data
) {
}
