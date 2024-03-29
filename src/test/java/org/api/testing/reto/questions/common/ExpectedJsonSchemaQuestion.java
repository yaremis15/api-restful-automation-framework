package org.api.testing.reto.questions.common;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Question;
import net.serenitybdd.screenplay.annotations.Subject;

import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static net.serenitybdd.screenplay.rest.questions.ResponseConsequence.seeThatResponse;
import static org.api.testing.reto.utils.constants.Constants.SUCCESSFUL_VALIDATION;

@Subject("the response schema matches json schema defined in file '#fieldName'")
public class ExpectedJsonSchemaQuestion implements Question<Boolean> {

    private final String fieldName;

    public ExpectedJsonSchemaQuestion(String fieldName) {
        this.fieldName = fieldName;
    }

    public static ExpectedJsonSchemaQuestion theJsonSchemaExpectIs(String fieldName) {
        return new ExpectedJsonSchemaQuestion(fieldName);
    }

    @Override
    public Boolean answeredBy(Actor actor) {
        actor.should(
                seeThatResponse(SUCCESSFUL_VALIDATION,
                        response -> response.assertThat()
                                .body(matchesJsonSchemaInClasspath("schemas/" + fieldName + ".json"))
                )
        );
        return true;
    }
}