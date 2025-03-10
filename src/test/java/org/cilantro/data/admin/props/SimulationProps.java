package org.cilantro.data.admin.props;

import lombok.Data;
import java.util.List;

@Data
public class SimulationProps {

    private List<String> initiateMenuPlanLabels;
    private List<String> initiateMenuPlanFieldLabels;
    private List<String> headerTabTxt;
    private String       modalHeadingTxt;
    private String       modalSubheadingTxt;
    private String       dishHeadingUnderDrpdn;
    private List<String> pageHeaderDrpdn;
    private String       pageHeading;
    private String       removeCategoryHeading;
    private String       removeCategorySubheading;
    private String       searchMinCharLimit;
}
