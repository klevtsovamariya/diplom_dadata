package models.cleaner;

import java.util.List;

public record RecordBodyModel(
        List<String> structure,
        List<List<String>> data
) {
}
