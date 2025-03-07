package org.cilantro.api.restful.request;

import org.cilantro.builders.ApiRequest;
import org.cilantro.enums.RequestMethod;

import java.util.Map;

public class RoleMaster {

    public static ApiRequest getRoleMasterLogsRequest (Map<String, String> header) {
        return ApiRequest.createRequest ()
                .method (RequestMethod.GET)
                .headers (header)
                .path ("roles")
                .create ();
    }

}
