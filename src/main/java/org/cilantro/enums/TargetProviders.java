package org.cilantro.enums;

import static org.cilantro.enums.Protocol.HTTP;
import static org.cilantro.enums.Protocol.HTTPS;
import static org.apache.commons.lang3.StringUtils.EMPTY;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * Supported cloud providers.
 *
 * @author Rahul Saini
 * @since 24-Dec-2024
 */
@Getter
@AllArgsConstructor
public enum TargetProviders {
    /**
     * BrowserStack.
     */
    BROWSER_STACK ("hub-cloud.browserstack.com", "bstack", HTTPS),
    /**
     * Lambda Test for Mobile.
     */
    LAMBDA_TEST_MOBILE ("mobile-hub.lambdatest.com", "lt", HTTPS),
    /**
     * Lambda Test for Web.
     */
    LAMBDA_TEST_WEB ("hub.lambdatest.com", "lt", HTTPS),
    /**
     * None.
     */
    LOCAL ("127.0.0.1", EMPTY, HTTP);

    private final String   host;
    private final String   prefix;
    private final Protocol protocol;
}
