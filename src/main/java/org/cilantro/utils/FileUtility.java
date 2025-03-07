package org.cilantro.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.apache.commons.io.FileUtils;

public final class FileUtility {

    public static File getFile (final String fileName) {
        if (FileUtility.class.getClassLoader ()
            .getResourceAsStream (fileName) != null) {
            final InputStream resourceAsStream = FileUtility.class.getClassLoader ()
                .getResourceAsStream (fileName);
            final File file = new File (fileName, "");
            try {
                assert resourceAsStream != null;
                FileUtils.copyInputStreamToFile (resourceAsStream, file);
            } catch (final IOException e) {
                throw new RuntimeException (e);
            }
            return file;
        } else {
            return new File (fileName);
        }
    }

    public static Properties readPropertyFile (final String filePath) {
        try (final FileInputStream input = new FileInputStream (filePath)) {
            final Properties prop = new Properties ();
            prop.load (input);
            return prop;
        } catch (final IOException ex) {
            throw new RuntimeException ("Exception occurred for filePath" + filePath + ":" + ex.getMessage ());
        }
    }
}
