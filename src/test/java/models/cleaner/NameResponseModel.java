package models.cleaner;

public record NameResponseModel(
        String source,
        String result,
        String surname,
        String name,
        String patronymic,
        String gender,
        Integer qc
) {
}
