package org.cilantro.api.restful.response;

import lombok.Data;
import lombok.ToString;

import java.util.List;

@Data
@ToString
public class RoleMasterResponseDate {

    private List<RoleMaster> data;
}
