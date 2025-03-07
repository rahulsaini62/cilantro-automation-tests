package org.cilantro.api.restful.response;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class RoleMaster {
    private String code;
    private String name;
    private String createdBy;
    private String createdOn;
    private String modifiedBy;
    private String modifiedOn;
    private boolean isActive;
}