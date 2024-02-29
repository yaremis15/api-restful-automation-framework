package org.api.testing.reto.steps.created;

import io.cucumber.java.es.Cuando;
import io.cucumber.java.es.Entonces;
import org.api.testing.reto.models.common.BookingData;
import org.api.testing.reto.tasks.booking.created.CreateBookingTask;
import org.api.testing.reto.tasks.booking.created.ValidateInCreatedBooking;


import java.util.List;
import java.util.Map;

import static net.serenitybdd.screenplay.GivenWhenThen.seeThat;
import static net.serenitybdd.screenplay.actors.OnStage.theActorInTheSpotlight;

import static org.api.testing.reto.questions.common.ExpectedJsonSchemaQuestion.theJsonSchemaExpectIs;
import static org.api.testing.reto.questions.common.ResponseTimeQuestion.responseTimeIs;
import static org.api.testing.reto.questions.common.StatusCodeQuestion.httpResponseStatusCodeIs;
import static org.api.testing.reto.steps.hooks.Actors.YAREMIS;
import static org.api.testing.reto.utils.constants.Constants.BOOKING_DATA;
import static org.api.testing.reto.utils.constants.Constants.CREATE_BOOKING_SHEMA;
import static org.api.testing.reto.utils.enums.HttpStatusCodes.OK;
import static org.api.testing.reto.utils.exceptions.AssertionsServices.*;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.lessThanOrEqualTo;

public class CreatedBookingSteps {
    @Cuando("el/ella solicita la creación de una reserva")
    public void sendRequestToApi() {
        List<Map<String, String>> datos = YAREMIS.recall(BOOKING_DATA);

        for (Map<String, String> data : datos) {

            theActorInTheSpotlight().attemptsTo(CreateBookingTask.withInformationRequested(data));

            theActorInTheSpotlight().should(seeThat(httpResponseStatusCodeIs(), equalTo(OK.getHttpStatusCode()))
                    .orComplainWith(AssertionError.class, THE_STATUS_CODE_SERVICE_IS_NOT_EXPECTED));

            theActorInTheSpotlight().should(seeThat(responseTimeIs(), lessThanOrEqualTo(10000L))
                    .orComplainWith(AssertionError.class, THE_RESPONSE_TIME_SERVICE_IS_NOT_EXPECTED));

            theActorInTheSpotlight().should(seeThat(theJsonSchemaExpectIs(CREATE_BOOKING_SHEMA))
                    .orComplainWith(AssertionError.class, THE_SCHEMA_SERVICE_IS_NOT_EXPECTED));
        }
    }

    @Entonces("su solicitud se creará en el sistema con su información y un número de registro único")
    public void validateServiceResponse() {
        ValidateInCreatedBooking.thatBookingIdWasGenerated();
        ValidateInCreatedBooking.thatFirstNameIs(BookingData.getData().get("firstname").toString());
    }
}
