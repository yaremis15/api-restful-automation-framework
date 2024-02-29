package org.api.testing.reto.tasks;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.thucydides.core.annotations.Step;
import org.api.testing.reto.interactions.ExecuteAuthRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static net.serenitybdd.rest.SerenityRest.lastResponse;
import static net.serenitybdd.screenplay.Tasks.instrumented;

import static org.api.testing.reto.models.headers.GetHeaderModel.headersDefault;
import static org.api.testing.reto.models.request.CreateTokenBuilder.Builder.andRequestBody;
import static org.api.testing.reto.steps.hooks.Actors.YAREMIS;
import static org.api.testing.reto.utils.common.JsonUtils.parseJsonObject;
import static org.api.testing.reto.utils.constants.Constants.TOKEN;
import static org.api.testing.reto.utils.environments.Endpoints.*;

public class CreateTokenTask implements Task {
    private static final Logger LOGGER = LoggerFactory.getLogger(CreateTokenTask.class.getSimpleName());

    public CreateTokenTask() {
    }

    public static CreateTokenTask createTokenInTheSystem() {
        return instrumented(CreateTokenTask.class);
    }

    @Override
    @Step("create a token")
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(ExecuteAuthRequest.with(
                AUTH,
                headersDefault(),
                andRequestBody()
                        .withUsername(USER_NAME)
                        .andPassword(PASSWORD)
                        .build()
        ));
        LOGGER.info("Response Body Is: ");
        lastResponse().getBody().prettyPeek();

        String token = parseJsonObject(lastResponse().getBody().asString()).get("token").getAsString();
        LOGGER.info("token is: {}", token);
        YAREMIS.remember(TOKEN, token);
    }
}
