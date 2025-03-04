package org.cilantro.actions.elements;

import static org.cilantro.actions.CommonActions.pause;
import static org.cilantro.actions.CommonActions.performElementAction;
import static org.cilantro.enums.ApplicationType.WEB;
import static org.cilantro.enums.ListenerType.TEXT_BOX_ACTION;
import static org.cilantro.enums.PlatformType.IOS;
import static org.cilantro.manager.ParallelSession.getSession;
import static java.util.Optional.ofNullable;
import static org.apache.logging.log4j.LogManager.getLogger;

import org.cilantro.actions.interfaces.elements.ITextBoxActions;
import org.cilantro.actions.interfaces.listeners.elements.ITextBoxActionsListener;
import org.cilantro.builders.Locator;
import org.apache.logging.log4j.Logger;

/**
 * All text box related actions
 *
 * @author Rahul Saini
 * @since 17-Dec-2024
 */
public class TextBoxActions extends ClickableActions implements ITextBoxActions {
    private static final Logger LOGGER = getLogger ();

    /**
     * Gets instance for text box actions class
     *
     * @param locator Locator of the element
     *
     * @return Instance of Text box actions class
     */
    public static ITextBoxActions onTextBox (final Locator locator) {
        return new TextBoxActions (locator);
    }

    private final ITextBoxActionsListener listener;

    private TextBoxActions (final Locator locator) {
        super (locator);
        this.listener = getSession ().getListener (TEXT_BOX_ACTION);
    }

    @Override
    public void enterText (final String text) {
        LOGGER.traceEntry ();
        LOGGER.info ("Entering text {} to element {}", text, this.locator.getName ());
        ofNullable (this.listener).ifPresent (l -> l.onEnterText (this.locator, text));
        pause (this.delaySetting.getBeforeTyping ());
        performElementAction (e -> {
            e.sendKeys (text);
            if (getSession ().getPlatformType () == IOS && getSession ().getMobileSetting ()
                .getDevice ()
                .getApplication ()
                .getType () != WEB) {
                e.sendKeys ("\n");
            }
        }, this.locator);
        LOGGER.traceExit ();
    }

    public void sendKeys(final String text) {
        ElementFinder.find (locator).sendKeys (text);
    }
}
