package org.cilantro.api.ssl;

import static org.cilantro.actions.api.ApiActions.withRequest;
import static org.cilantro.enums.PlatformType.API;
import static org.cilantro.enums.RequestMethod.GET;
import static org.cilantro.manager.ParallelSession.clearSession;
import static org.cilantro.manager.ParallelSession.createSession;

import org.cilantro.builders.ApiRequest;
import org.testng.annotations.Test;

/**
 * Test Bad SSL API.
 *
 * @author Rahul Saini
 * @since 28-Dec-2024
 */
public class ApiSecurityTest {
    /**
     * Test bad host name.
     */
    @Test
    public void testBadHostName () {
        try {
            createSession (API, "test_bad_host_name_wo_verify_hn");
            final var request = ApiRequest.createRequest ()
                .method (GET)
                .create ();
            final var response = withRequest (request).execute ();
            response.verifyStatusCode ()
                .isEqualTo (200);
        } finally {
            clearSession ();
        }
    }

    /**
     * Test Bad SSL API.
     */
    @Test
    public void testBadSsl () {
        try {
            createSession (API, "test_bad_ssl_wo_verify");
            final var request = ApiRequest.createRequest ()
                .method (GET)
                .create ();
            final var response = withRequest (request).execute ();
            response.verifyStatusCode ()
                .isEqualTo (200);
        } finally {
            clearSession ();
        }
    }
}
