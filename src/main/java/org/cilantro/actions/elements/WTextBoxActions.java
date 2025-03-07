package org.cilantro.actions.elements;

import static java.util.Optional.ofNullable;
import static org.apache.logging.log4j.LogManager.getLogger;
import static org.cilantro.actions.CommonActions.pause;
import static org.cilantro.actions.CommonActions.performElementAction;
import static org.cilantro.enums.ApplicationType.WEB;
import static org.cilantro.enums.ListenerType.TEXT_BOX_ACTION;
import static org.cilantro.enums.PlatformType.IOS;
import static org.cilantro.manager.ParallelSession.getSession;

import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebElement;
import org.cilantro.actions.interfaces.elements.ITextBoxActions;
import org.cilantro.actions.interfaces.listeners.elements.ITextBoxActionsListener;

public class WTextBoxActions extends WClickableActions implements ITextBoxActions {
    private static final Logger LOGGER = getLogger ();

    /**
     * Gets instance for text box actions class
     *
     * @param element WebElement
     *
     * @return Instance of Text box actions class
     */
    public static ITextBoxActions onTextBox (final WebElement element) {
        return new WTextBoxActions (element);
    }

    private final ITextBoxActionsListener listener;

    private WTextBoxActions (final WebElement element) {
        super (element);
        this.listener = getSession ().getListener (TEXT_BOX_ACTION);
    }

    /**
     * Enter text in text field.
     *
     * @param text text to enter
     */
    @Override
    public void enterText (final String text) {
        LOGGER.traceEntry ();
        LOGGER.info ("Entering text {} to element {}", text, this.element.getText ());
        ofNullable (this.listener).ifPresent (l -> l.onEnterText (this.element, text));
        pause (this.delaySetting.getBeforeTyping ());
        performElementAction (e -> {
            e.sendKeys (text);
            if (getSession ().getPlatformType () == IOS && getSession ().getMobileSetting ()
                .getDevice ()
                .getApplication ()
                .getType () != WEB) {
                e.sendKeys ("\n");
            }
        }, this.element);
        LOGGER.traceExit ();
    }

    /**
     * @param countryName
     */
    @Override
    public void sendKeys (final String countryName) {
    }
}
