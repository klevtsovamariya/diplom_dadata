package models.suggestions;

public record SuggestionEmailModel(
        String value,
        String unrestricted_value,
        EmailSuggestionDataModel data
) {
}
