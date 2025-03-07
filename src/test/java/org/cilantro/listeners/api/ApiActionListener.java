package org.cilantro.listeners.api;

import static io.qameta.allure.Allure.addAttachment;
import static io.qameta.allure.Allure.step;
import static java.lang.String.format;
import static java.util.Optional.ofNullable;

import org.cilantro.actions.interfaces.listeners.api.IApiActionsListener;
import org.cilantro.builders.ApiResponse;

/**
 * API Actions listener.
 *
 * @author Rahul Saini
 * @since 06-Dec-2024
 */
public class ApiActionListener implements IApiActionsListener {
    @Override
    public void onExecute (final ApiResponse response) {
        final var request = response.getRequest ();
        step (format ("Executing [{0}] API request", request.getMethod ()));
        addAttachment ("Status Code", Integer.toString (response.getStatusCode ()));
        ofNullable (request.getBody ()).ifPresent (v -> addAttachment ("Request Body", v));
        ofNullable (response.getBody ()).ifPresent (v -> addAttachment ("Response Body", v));
    }
}
