package org.api.testing.reto.steps.common;

import io.cucumber.java.es.Dado;
import io.cucumber.java.es.Y;
import net.serenitybdd.screenplay.actors.OnStage;
import net.serenitybdd.screenplay.rest.abilities.CallAnApi;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static net.serenitybdd.screenplay.actors.OnStage.theActorInTheSpotlight;
import static org.api.testing.reto.steps.hooks.Actors.YAREMIS;
import static org.api.testing.reto.utils.constants.Constants.THE_REST_API_BASE_URL;


public class CommonSteps {

    @Dado("que la/el cliente desea crear/consultar/actualizar/eliminar una/la reservación de/para su próximo viaje")
    public void preparingAPI() {
        OnStage.theActorCalled(YAREMIS.toString());
        theActorInTheSpotlight()
                .whoCan(CallAnApi.at(YAREMIS.recall(THE_REST_API_BASE_URL)));
    }

    @Y("se carga su información al sistema")
    public void loadTestDataIntoSystem(List<Map<String, String>> datos) {


    }
}
