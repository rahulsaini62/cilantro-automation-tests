package org.cilantro.pages;

import lombok.Getter;
import org.cilantro.builders.Locator;

import static io.appium.java_client.AppiumBy.accessibilityId;
import static org.openqa.selenium.By.cssSelector;
import static org.openqa.selenium.By.xpath;

@Getter
public class LoginPage {
    private static final LoginPage LOGIN_PAGE = new LoginPage();

    /**
     * Gets Login page instance.
     *
     * @return Login page instance
     */
    public static LoginPage loginPage() {
        return LOGIN_PAGE;
    }

    private final Locator logo = Locator.buildLocator()
            .web(cssSelector("div.textWrap img[src*='assets']"))
            .android(accessibilityId("test-CHECKOUT"))
            .ios(accessibilityId("test-CHECKOUT"))
            .name("Logo")
            .build();

    private final Locator pageHeading = Locator.buildLocator()
            .web(cssSelector("div.headingWrap h1"))
            .name("Page Heading")
            .build();

    private final Locator pageSubHeading = Locator.buildLocator()
            .web(cssSelector("div.headingWrap p"))
            .name("Page Sub Heading")
            .build();

    private final Locator getRedirectedBtn = Locator.buildLocator()
            .web(cssSelector("div.headingWrap button.button"))
            .name("Get Redirected Btn")
            .build();

    private final Locator emailTxt = Locator.buildLocator()
            .web(cssSelector("input[name='loginfmt']"))
            .name("Email Text")
            .build();

    private final Locator nextBtn = Locator.buildLocator()
            .web(cssSelector("input#idSIButton9"))
            .name("Next Button")
            .build();

    private final Locator passwordTxt = Locator.buildLocator()
            .web(cssSelector("input[name='passwd']"))
            .name("Password Text")
            .build();

    private final Locator signInBtn = Locator.buildLocator()
            .web(cssSelector("button#idSIButton9"))
            .name("Sign In Button")
            .build();

    private final Locator staySignInBtn = Locator.buildLocator()
            .web(xpath("//button[@id='acceptButton']"))
            .name("Stay Sign-in Button")
            .build();
}
