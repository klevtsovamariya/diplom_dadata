package models.cleaner;

import java.util.List;

public record NameRecordResponseModel(
        List<String> structure,
        List<List<NameResponseModel>> data
) {
}
