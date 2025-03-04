package org.cilantro.builders;

import java.util.Map;

import org.cilantro.enums.ContentType;
import org.cilantro.enums.RequestMethod;
import lombok.Builder;
import lombok.Getter;
import lombok.Singular;
import lombok.ToString;

/**
 * Request builder class.
 *
 * @author Rahul Saini
 * @since 04-Dec-2024
 */
@ToString
@Getter
@Builder (builderMethodName = "createRequest", buildMethodName = "create")
public class ApiRequest {
    private String              body;
    private Object              bodyObject;
    private ContentType         contentType;
    @Singular
    private Map<String, String> formBodies;
    @Singular
    private Map<String, String> headers;
    private RequestMethod       method;
    private String              password;
    private String              path;
    @Singular
    private Map<String, String> pathParams;
    @Singular
    private Map<String, String> queryParams;
    private String              userName;
}
