package org.cilantro.pages;

import lombok.Getter;
import org.cilantro.builders.Locator;

import static io.appium.java_client.AppiumBy.accessibilityId;
import static org.openqa.selenium.By.cssSelector;
import static org.openqa.selenium.By.xpath;

@Getter
public class DashboardPage {
    private static final DashboardPage COMMON_PAGE = new DashboardPage();

    /**
     * Gets Common page instance.
     *
     * @return Common page instance
     */
    public static DashboardPage commonPage() {
        return COMMON_PAGE;
    }

    private final Locator tableLoader = Locator.buildLocator()
            .web(xpath("//table[@aria-label='loading table']"))
            .name("Table Loader")
            .build();

    private final Locator pageLoader = Locator.buildLocator()
            .web(cssSelector("svg.MuiCircularProgress-svg.css-13o7eu2"))
            .name("Page Loader")
            .build();

    public final Locator getHeaderTabUnderMenu(String tabName){
        return Locator.buildLocator()
                .web(xpath("//button[text()='"+tabName+"']"))
                .android(accessibilityId("test-CHECKOUT"))
                .ios(accessibilityId("test-CHECKOUT"))
                .name(tabName+ " tab under Menu")
                .build();
    }

    public final Locator getSubTabUnderMenu(String tabName){
        return Locator.buildLocator()
                .web(xpath("//a[text()='"+tabName+"']"))
                .android(accessibilityId("test-CHECKOUT"))
                .ios(accessibilityId("test-CHECKOUT"))
                .name(tabName+ " tab under Menu")
                .build();
    }

    private final Locator pageTitle = Locator.buildLocator()
            .web(xpath("//h1[text()='Dashboard']"))
            .name("Dashboard Page Title")
            .build();

    private final Locator userImage = Locator.buildLocator()
            .web(cssSelector("#basic-button"))
            .name("User image")
            .build();

}