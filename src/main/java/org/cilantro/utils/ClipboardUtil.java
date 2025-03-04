package org.cilantro.utils;

import static com.google.common.truth.Truth.assertThat;
import static org.apache.logging.log4j.LogManager.getLogger;

import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;

import com.google.common.truth.StringSubject;
import org.apache.logging.log4j.Logger;

public final class ClipboardUtil {
    private static final Logger LOGGER = getLogger ();

    public static StringSubject verifyCopiedText () {
        String text = null;
        try {
            // Get the system clipboard
            final Clipboard clipboard = Toolkit.getDefaultToolkit ()
                .getSystemClipboard ();
            // Get the contents of the clipboard
            final Transferable contents = clipboard.getContents (null);
            // Check if the contents are text
            if (contents != null && contents.isDataFlavorSupported (DataFlavor.stringFlavor)) {
                // Retrieve the text from the clipboard
                text = (String) contents.getTransferData (DataFlavor.stringFlavor);
                System.out.println ("Text from clipboard: " + text);
            } else {
                System.out.println ("Clipboard does not contain text");
            }
        } catch (Exception e) {
            LOGGER.info (e);
        }
        return assertThat (text);
    }

    public static String getCopiedText () {
        String text = null;
        try {
            // Get the system clipboard
            final Clipboard clipboard = Toolkit.getDefaultToolkit ()
                .getSystemClipboard ();
            // Get the contents of the clipboard
            final Transferable contents = clipboard.getContents (null);
            // Check if the contents are text
            if (contents != null && contents.isDataFlavorSupported (DataFlavor.stringFlavor)) {
                // Retrieve the text from the clipboard
                text = (String) contents.getTransferData (DataFlavor.stringFlavor);
                System.out.println ("Text from clipboard: " + text);
            } else {
                System.out.println ("Clipboard does not contain text");
            }
        } catch (final Exception e) {
            LOGGER.info (e);
        }
        return text;
    }
}
