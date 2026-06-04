package models.suggestions;

import java.util.List;

public record SuggestResponseEmailModel(
        List<SuggestionEmailModel> suggestions
) {
}
