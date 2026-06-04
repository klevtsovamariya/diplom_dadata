package models.cleaner;

public record PhoneResponseModel(
        String source,
        String type,
        String phone,
        String country_code,
        String city_code,
        String number,
        Integer qc
) {
}
