package org.cilantro.builders;

import static org.cilantro.manager.ParallelSession.getSession;

import java.util.function.Predicate;

import org.cilantro.enums.ApplicationType;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;
import org.jetbrains.annotations.NotNull;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

/**
 * UI application locator.
 *
 * @author Rahul Saini
 * @since 25-Dec-2024
 */
@ToString
@Getter
@Builder (builderMethodName = "buildLocator")
public class Locator {
    private By                    android;
    private Predicate<WebElement> filter;
    private int                   index;
    private By                    ios;
    @NotNull
    private String                name;
    private Locator               parent;
    private By                    web;

    /**
     * Gets the platform specific locator
     *
     * @return Locator for the element
     */
    public By getLocator () {
        switch (getSession ().getPlatformType ()) {
            case ANDROID:
                return getMobileOrWebLocator (this.android);
            case IOS:
                return getMobileOrWebLocator (this.ios);
            case WEB:
            default:
                return this.web;
        }
    }

    private By getMobileOrWebLocator (final By locator) {
        if (getSession ().getMobileSetting ()
            .getDevice ()
            .getApplication ()
            .getType () == ApplicationType.WEB) {
            return this.web;
        }
        return locator;
    }
}
