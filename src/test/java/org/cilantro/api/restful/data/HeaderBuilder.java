package org.cilantro.api.restful.data;

import static org.cilantro.manager.ParallelSession.getSession;

import java.util.HashMap;
import java.util.Map;


public class HeaderBuilder {

    public Map<String, String> buildHeaders() {
        Map<String, String> headers = new HashMap<>();
        headers.put("authorization", getSession().getSharedData("token"));
        headers.put("x-api-key", "FhPYSItYj0iLFj3PElgU2g==");
//        headers.put("device_id", getSession().getSharedData("deviceId"));
        return headers;
    }

//    public Map<String, String> query() {
//        Map<String, String> queryParams = new HashMap<>();
//        queryParams.put("page", "1");
//        queryParams.put("limit", "10");
//        return queryParams;
//    }

//    public Map<String, String> listingQuery() {
//        final HashMap<String, String> query = new HashMap<>();
//        query.put("page", "1");
//        query.put("limit", "50");
//        query.put("is_drop_down", "false");
//        return query;
//    }


}
