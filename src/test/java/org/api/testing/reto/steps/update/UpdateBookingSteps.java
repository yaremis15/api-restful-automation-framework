package org.api.testing.reto.steps.update;

import io.cucumber.java.es.Cuando;
import io.cucumber.java.es.Entonces;
import org.api.testing.reto.questions.common.TheQueryFieldsAndValuesAre;
import org.api.testing.reto.tasks.common.ConsumePatchTask;
import org.api.testing.reto.tasks.common.ConsumePutTask;
import org.api.testing.reto.tasks.common.LoadDataTask;
import org.api.testing.reto.utils.exceptions.AssertionsServices;
import org.api.testing.reto.utils.exceptions.NotQueryParameterFoundException;

import java.util.List;
import java.util.Map;

import static net.serenitybdd.screenplay.GivenWhenThen.seeThat;
import static net.serenitybdd.screenplay.actors.OnStage.theActorInTheSpotlight;

import static org.api.testing.reto.models.headers.GetHeaderModel.headersAuthentication;
import static org.api.testing.reto.questions.common.ExpectedJsonSchemaQuestion.theJsonSchemaExpectIs;
import static org.api.testing.reto.questions.common.GetValueFromResponseBodyQuestion.theAttributeValue;
import static org.api.testing.reto.questions.common.ResponseTimeQuestion.responseTimeIs;
import static org.api.testing.reto.questions.common.StatusCodeQuestion.httpResponseStatusCodeIs;
import static org.api.testing.reto.steps.hooks.Actors.YAREMIS;
import static org.api.testing.reto.utils.constants.Constants.*;
import static org.api.testing.reto.utils.enums.HttpStatusCodes.OK;
import static org.api.testing.reto.utils.exceptions.AssertionsServices.*;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.*;

public class UpdateBookingSteps {
    @Cuando("ella/el ingrese todos los datos de su reserva con las actualizaciones deseadas")
    public void updateBooking(List<Map<String, String>> datos) {
        theActorInTheSpotlight().wasAbleTo(LoadDataTask.informationBooking(datos.get(0)));

        for (Map<String, String> newData : datos) {
            theActorInTheSpotlight().attemptsTo(
                    ConsumePutTask.withNewInformationRequested(YAREMIS.recall(BOOKING_ID), newData, headersAuthentication()));
        }
    }

    @Cuando("ella/el solicita la actualización para la fecha de ingreso {string} & la fecha de salida {string}")
    public void partialBookingUpdate(String checkIn, String checkOut) {
        YAREMIS.remember(CHECKIN_DATE, checkIn);
        YAREMIS.remember(CHECKOUT_DATE, checkOut);

        theActorInTheSpotlight().attemptsTo(
                ConsumePatchTask.withNewInformationRequested(
                        YAREMIS.recall(BOOKING_ID), checkIn, checkOut, headersAuthentication()));
    }

    @Entonces("visualizará los {} de la reserva con su/la nueva información")
    public void validateTheBookingUpdate(String option) {

        switch (option) {
            case "detalles":
                theActorInTheSpotlight()
                        .should(seeThat(TheQueryFieldsAndValuesAre.expected())
                                .orComplainWith(AssertionsServices.class, AssertionsServices.THE_VALUES_SERVICE_IS_NOT_EXPECTED)
                        );
                break;

            case "campos":
                theActorInTheSpotlight().should(
                        seeThat("the checkIn Date",
                                theAttributeValue("bookingdates.checkin"), is(equalTo(YAREMIS.recall(CHECKIN_DATE))))
                                .orComplainWith(AssertionsServices.class, AssertionsServices.THE_VALUES_SERVICE_IS_NOT_EXPECTED));

                theActorInTheSpotlight().should(
                        seeThat("the checkOut Date",
                                theAttributeValue("bookingdates.checkout"), is(equalTo(YAREMIS.recall(CHECKOUT_DATE))))
                                .orComplainWith(AssertionsServices.class, AssertionsServices.THE_VALUES_SERVICE_IS_NOT_EXPECTED));
                break;

            default:
                throw new NotQueryParameterFoundException(option);

        }
        theActorInTheSpotlight().should(seeThat(httpResponseStatusCodeIs(), equalTo(OK.getHttpStatusCode()))
                .orComplainWith(AssertionError.class, THE_STATUS_CODE_SERVICE_IS_NOT_EXPECTED));

        theActorInTheSpotlight().should(seeThat(responseTimeIs(), lessThanOrEqualTo(10000L))
                .orComplainWith(AssertionError.class, THE_RESPONSE_TIME_SERVICE_IS_NOT_EXPECTED));

        theActorInTheSpotlight().should(seeThat(theJsonSchemaExpectIs(GET_BOOKING_DETAILS_SCHEMA))
                .orComplainWith(AssertionError.class, THE_SCHEMA_SERVICE_IS_NOT_EXPECTED));
    }
}
