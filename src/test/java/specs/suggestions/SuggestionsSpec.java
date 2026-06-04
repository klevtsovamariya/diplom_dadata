package specs.suggestions;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import tests.api.TestConfig;

import static io.restassured.filter.log.LogDetail.ALL;
import static io.restassured.http.ContentType.JSON;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static org.hamcrest.Matchers.notNullValue;
import static specs.BaseSpec.baseRequestSpec;

public class SuggestionsSpec {

    public static RequestSpecification suggestionsRequestSpec = new RequestSpecBuilder()
            .addRequestSpecification(baseRequestSpec)
            .setBaseUri(TestConfig.suggestionsBaseUrl())
            .build();

    public static ResponseSpecification successfulSuggestResponseSpec = new ResponseSpecBuilder()
            .log(ALL)
            .expectStatusCode(200)
            .expectContentType(JSON)
            .expectBody(matchesJsonSchemaInClasspath("schemas/suggestions/successful_suggest_response_schema.json"))
            .expectBody("suggestions", notNullValue())
            .build();
}
