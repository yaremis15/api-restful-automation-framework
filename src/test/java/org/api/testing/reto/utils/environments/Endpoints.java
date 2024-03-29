package org.api.testing.reto.utils.environments;

import net.serenitybdd.core.environment.EnvironmentSpecificConfiguration;
import org.api.testing.reto.steps.hooks.CommonHooks;

public class Endpoints {
    public static final String BASE_URL = setEndpoint("baseUrl");
    public static final String AUTH = setEndpoint("auth");
    public static final String GET_ALL_BOOKINGS = setEndpoint("getAllBookings");
    public static final String GET_BOOKING_BY_ID = setEndpoint("getBookingId");
    public static final String GET_BOOKING_BY_CUSTOM_NAMES = setEndpoint("getBookingByCustomNames");
    public static final String GET_BOOKING_BY_DATES = setEndpoint("getBookingByDates");
    public static final String CREATE_BOOKING = setEndpoint("createBooking");
    public static final String UPDATE_BOOKING = setEndpoint("updateBookingId");
    public static final String PARTIAL_UPDATE_BOOKING = setEndpoint("partialUpdateBooking");
    public static final String DELETE_BOOKING = setEndpoint("deleteBooking");
    public static final String HEALTH_CHECK = setEndpoint("healthCheckApi");
    public static final String USER_NAME = setEndpoint("username");
    public static final String PASSWORD = setEndpoint("password");

    private Endpoints() {
        //Nothing
    }

    public static String setEndpoint(String path) {
        return EnvironmentSpecificConfiguration.from(CommonHooks.environmentVariables).getProperty(path);
    }
}
