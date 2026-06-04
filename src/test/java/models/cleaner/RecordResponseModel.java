package models.cleaner;

import java.util.List;

public record RecordResponseModel(
        List<String> structure,
        List<List<Object>> data
) {
}
