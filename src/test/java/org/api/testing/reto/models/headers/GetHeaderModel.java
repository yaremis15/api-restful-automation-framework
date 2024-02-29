package org.api.testing.reto.models.headers;

import java.util.HashMap;
import java.util.Map;

import static org.api.testing.reto.steps.hooks.Actors.YAREMIS;
import static org.api.testing.reto.utils.constants.Constants.TOKEN;


public class GetHeaderModel {

    private static final Map<String, String> headers = new HashMap<>();

    private GetHeaderModel() {
    }

    public static Map<String, String> headersDefault() {
        headers.put(HeaderValueModel.CONTENT_TYPE.getHeader(), HeaderValueModel.CONTENT_TYPE.getValue());
        headers.put(HeaderValueModel.ACCEPT.getHeader(), HeaderValueModel.ACCEPT.getValue());
        return headers;
    }

    public static Map<String, String> headersAuthentication() {
        String token = "token=" + YAREMIS.recall(TOKEN);

        headers.put(HeaderValueModel.CONTENT_TYPE.getHeader(), HeaderValueModel.CONTENT_TYPE.getValue());
        headers.put(HeaderValueModel.ACCEPT.getHeader(), HeaderValueModel.ACCEPT.getValue());
        headers.put(HeaderValueModel.COOKIE.getHeader(), token);
        return headers;
    }
}
