package specs.cleaner;

import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

import static io.restassured.filter.log.LogDetail.ALL;
import static io.restassured.http.ContentType.JSON;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static org.hamcrest.Matchers.anyOf;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;
import static specs.BaseSpec.baseRequestSpec;

public class CleanerSpec {

    public static RequestSpecification cleanerRequestSpec = baseRequestSpec;

    public static ResponseSpecification successfulCleanArrayResponseSpec = new ResponseSpecBuilder()
            .log(ALL)
            .expectStatusCode(200)
            .expectContentType(JSON)
            .expectBody(matchesJsonSchemaInClasspath("schemas/cleaner/successful_clean_simple_response_schema.json"))
            .expectBody("[0].source", notNullValue())
            .build();

    public static ResponseSpecification successfulCleanRecordResponseSpec = new ResponseSpecBuilder()
            .log(ALL)
            .expectStatusCode(200)
            .expectContentType(JSON)
            .expectBody(matchesJsonSchemaInClasspath("schemas/cleaner/successful_clean_record_response_schema.json"))
            .expectBody("structure", notNullValue())
            .expectBody("data", notNullValue())
            .build();

    public static ResponseSpecification unauthorizedCleanResponseSpec = new ResponseSpecBuilder()
            .log(ALL)
            .expectStatusCode(anyOf(is(401), is(403)))
            .build();
}
