package org.cilantro.config.api;

import lombok.Data;

/**
 * API setting class.
 *
 * @author Rahul Saini
 * @since 17-Dec-2024
 */
@Data
public class ApiSetting {
    private String     basePath          = "";
    private String     baseUri;
    private int        connectionTimeout = 5;
    private LogSetting logging           = new LogSetting ();
    private int        port;
    private int        readTimeout       = 5;
    private String     schemaPath        = "";
    private boolean    validateSsl       = true;
    private boolean    verifyHostName    = true;
    private int        writeTimeout      = 5;
}
