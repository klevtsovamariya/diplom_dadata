package models.suggestions;

import java.util.List;

public record SuggestResponseAddressModel(
        List<SuggestionAddressModel> suggestions
) {
}
