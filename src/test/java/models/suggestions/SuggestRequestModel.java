package models.suggestions;

public record SuggestRequestModel(
        String query,
        Integer count
) {
}
