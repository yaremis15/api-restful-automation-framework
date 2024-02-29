package org.api.testing.reto.steps.check;

import io.cucumber.java.es.Cuando;
import io.cucumber.java.es.Entonces;
import org.api.testing.reto.questions.common.TheQueryFieldsAndValuesAre;
import org.api.testing.reto.tasks.common.ConsumeGetTask;
import org.api.testing.reto.utils.exceptions.AssertionsServices;
import org.api.testing.reto.utils.exceptions.NotQueryParameterFoundException;


import static net.serenitybdd.screenplay.GivenWhenThen.seeThat;
import static net.serenitybdd.screenplay.actors.OnStage.theActorInTheSpotlight;

import static org.api.testing.reto.questions.common.ExpectedJsonSchemaQuestion.theJsonSchemaExpectIs;
import static org.api.testing.reto.questions.common.ResponseTimeQuestion.responseTimeIs;
import static org.api.testing.reto.questions.common.StatusCodeQuestion.httpResponseStatusCodeIs;
import static org.api.testing.reto.steps.hooks.Actors.YAREMIS;
import static org.api.testing.reto.tasks.common.ConsumeGetWithPathParamsTask.consumeGetWithPathParams;
import static org.api.testing.reto.utils.constants.Constants.*;
import static org.api.testing.reto.utils.enums.HttpStatusCodes.OK;
import static org.api.testing.reto.utils.environments.Endpoints.*;
import static org.api.testing.reto.utils.exceptions.AssertionsServices.*;
import static org.hamcrest.Matchers.*;

public class GetBookingSteps {
    @Cuando("ella/el filtre la consulta por el parámetro {string}")
    public void getScheduledReservations(String queryParametersOption) {

        switch (queryParametersOption) {
            case "All Bookings":
                theActorInTheSpotlight().attemptsTo(ConsumeGetTask.with(GET_ALL_BOOKINGS));

                theActorInTheSpotlight().should(seeThat(theJsonSchemaExpectIs(GET_ALL_BOOKINGS_SCHEMA))
                        .orComplainWith(AssertionError.class, THE_SCHEMA_SERVICE_IS_NOT_EXPECTED));
                break;

            case "ID Booking":
                theActorInTheSpotlight().attemptsTo(ConsumeGetTask.with(GET_BOOKING_BY_ID + YAREMIS.recall(BOOKING_ID)));

                theActorInTheSpotlight().should(seeThat(theJsonSchemaExpectIs(GET_BOOKING_DETAILS_SCHEMA))
                        .orComplainWith(AssertionError.class, THE_SCHEMA_SERVICE_IS_NOT_EXPECTED));
                break;

            case "Customer Names":
                theActorInTheSpotlight().attemptsTo(consumeGetWithPathParams(GET_BOOKING_BY_CUSTOM_NAMES, YAREMIS.recall(PATH_PARAMS_WITH_NAMES)));

                theActorInTheSpotlight().should(seeThat(theJsonSchemaExpectIs(GET_ALL_BOOKINGS_SCHEMA))
                        .orComplainWith(AssertionError.class, THE_SCHEMA_SERVICE_IS_NOT_EXPECTED));
                break;

            case "Dates":
                theActorInTheSpotlight().attemptsTo(consumeGetWithPathParams(GET_BOOKING_BY_DATES, YAREMIS.recall(PATH_PARAMS_WITH_DATES)));

                theActorInTheSpotlight().should(seeThat(theJsonSchemaExpectIs(GET_ALL_BOOKINGS_SCHEMA))
                        .orComplainWith(AssertionError.class, THE_SCHEMA_SERVICE_IS_NOT_EXPECTED));
                break;

            default:
                throw new NotQueryParameterFoundException(queryParametersOption);
        }

        theActorInTheSpotlight().should(seeThat(httpResponseStatusCodeIs(), equalTo(OK.getHttpStatusCode()))
                .orComplainWith(AssertionError.class, THE_STATUS_CODE_SERVICE_IS_NOT_EXPECTED));

        theActorInTheSpotlight().should(seeThat(responseTimeIs(), lessThanOrEqualTo(15000L))
                .orComplainWith(AssertionError.class, THE_RESPONSE_TIME_SERVICE_IS_NOT_EXPECTED));
    }

    @Entonces("visualizará los detalles de la reserva")
    public void validateBookingDetails() {
        theActorInTheSpotlight()
                .should(seeThat(TheQueryFieldsAndValuesAre.expected())
                        .orComplainWith(AssertionsServices.class, AssertionsServices.THE_VALUES_SERVICE_IS_NOT_EXPECTED)
                );
    }
}
