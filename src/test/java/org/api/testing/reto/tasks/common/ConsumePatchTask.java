package org.api.testing.reto.tasks.common;

import com.jayway.jsonpath.DocumentContext;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;

import org.api.testing.reto.interactions.ExecutePatchRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;

import static net.serenitybdd.rest.SerenityRest.lastResponse;
import static net.serenitybdd.screenplay.Tasks.instrumented;
import static org.api.testing.reto.models.request.CreateBookingRequestBuilder.Builder.andRequestBody;
import static org.api.testing.reto.steps.hooks.Actors.YAREMIS;
import static org.api.testing.reto.utils.common.JsonUtils.parseDocumentContextFromString;
import static org.api.testing.reto.utils.constants.Constants.RESPONSE_BODY;


public class ConsumePatchTask implements Task {
    private static final Logger LOGGER = LoggerFactory.getLogger(ConsumePatchTask.class.getSimpleName());
    private final int id;
    private final String checkInDate;
    private final String checkOutDate;
    private final Map<String, String> headers;

    public ConsumePatchTask(int id, String checkInDate, String checkOutDate, Map<String, String> headers) {
        this.id = id;
        this.checkInDate = checkInDate;
        this.checkOutDate = checkOutDate;
        this.headers = headers;
    }

    public static ConsumePatchTask withNewInformationRequested(int id, String checkInDate, String checkOutDate, Map<String, String> headers) {
        return instrumented(ConsumePatchTask.class, id, checkInDate, checkOutDate, headers);
    }

    @Override
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(ExecutePatchRequest
                .with(id,
                        headers,
                        andRequestBody()
                                .andBookingDates(checkInDate, checkOutDate)
                                .build()
                )
        );

        LOGGER.info("Response Body Is: ");
        lastResponse().getBody().prettyPeek();

        String response = lastResponse().getBody().asPrettyString();
        DocumentContext documentContextResponse = parseDocumentContextFromString(response);
        YAREMIS.remember(RESPONSE_BODY, documentContextResponse);
    }
}
