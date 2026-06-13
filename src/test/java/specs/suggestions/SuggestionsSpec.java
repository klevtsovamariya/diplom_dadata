package specs.suggestions;

import config.ApiConfig;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

import static io.restassured.filter.log.LogDetail.ALL;
import static io.restassured.http.ContentType.JSON;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static org.hamcrest.Matchers.notNullValue;
import static org.aeonbits.owner.ConfigFactory.create;
import static specs.BaseSpec.baseRequestSpec;

public class SuggestionsSpec {

    private static final ApiConfig config = create(ApiConfig.class, System.getProperties());

    public static RequestSpecification suggestionsRequestSpec = new RequestSpecBuilder()
            .addRequestSpecification(baseRequestSpec)
            .setBaseUri(config.suggestionsBaseUrl())
            .setBasePath("")
            .build();

    public static ResponseSpecification successfulSuggestResponseSpec = new ResponseSpecBuilder()
            .log(ALL)
            .expectStatusCode(200)
            .expectContentType(JSON)
            .expectBody(matchesJsonSchemaInClasspath("schemas/suggestions/successful_suggest_response_schema.json"))
            .expectBody("suggestions", notNullValue())
            .build();
}
