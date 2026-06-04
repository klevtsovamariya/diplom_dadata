package models.cleaner;

public record EmailResponseModel(
        String source,
        String email,
        String local,
        String domain,
        String type,
        Integer qc
) {
}
