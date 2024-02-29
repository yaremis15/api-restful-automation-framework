package org.api.testing.reto.steps.hooks;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.DataTableType;
import io.cucumber.java.Scenario;
import net.serenitybdd.screenplay.actors.OnStage;
import net.serenitybdd.screenplay.actors.OnlineCast;
import net.thucydides.core.util.EnvironmentVariables;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import static org.api.testing.reto.steps.hooks.Actors.YAREMIS;
import static org.api.testing.reto.utils.constants.Constants.SCENARIO;
import static org.api.testing.reto.utils.constants.Constants.THE_REST_API_BASE_URL;
import static org.api.testing.reto.utils.environments.Endpoints.BASE_URL;

public class CommonHooks {

    private static final Logger LOG = LoggerFactory.getLogger(CommonHooks.class.getSimpleName());
    public static EnvironmentVariables environmentVariables;

    @Before
    public void setTheStage(Scenario scenario) {
        OnStage.setTheStage(new OnlineCast());
        YAREMIS.remember(THE_REST_API_BASE_URL, BASE_URL);
        YAREMIS.remember(SCENARIO, scenario.getName());
        YAREMIS.describedAs("un usuario que puede crear, consultar, actualizar y eliminar su reserva");

        LOG.info("*****************************************************************************************");
        LOG.info("	Scenario: " + scenario.getName());
        LOG.info("	Tags: " + scenario.getSourceTagNames());
        LOG.info("*****************************************************************************************");
    }

    @After
    public void afterScenario(Scenario scenario) {
        //CAMILA.forget(BOOKING_DATA);
        LOG.info("*****************************************************************************************");
        LOG.info(("	Scenario: " + scenario.getName() + ": " + scenario.getStatus()));
        LOG.info("*****************************************************************************************");
    }


    /**
     * Al usar replaceWithEmptyString = "[blank]" se puede insertar explícitamente una cadena vacía.
     */
    @DataTableType(replaceWithEmptyString = "[blank]")
    public String stringType(String cell) {
        return cell;
    }
}