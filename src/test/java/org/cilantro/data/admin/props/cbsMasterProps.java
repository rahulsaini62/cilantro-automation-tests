package org.cilantro.data.admin.props;

import lombok.Data;

import java.util.List;

@Data
public class cbsMasterProps {

    private List<String> serviceTypeMasterTableColumn;
    private String roleMasterTitle;
    private List<String> roleMasterColumnTxt;
    private String createRoleTxt;
    private List<String> createRolePopupBtnWrapperTxt;
    private String createRolePopupTitle;
    private String roleNameTxt;
    private String createRoleTxtBoxPlaceholderTxt;
    private String duplicateRoleNameErrorMsg;
    private String createdSuccessMsg;
    private String discardChangesPopupTitle;
    private String discardChangesPopupMsg;

}
