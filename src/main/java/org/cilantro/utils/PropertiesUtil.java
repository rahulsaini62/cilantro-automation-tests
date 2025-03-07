package org.cilantro.utils;

import java.io.File;
import java.nio.file.Paths;

public final class PropertiesUtil {

    private static final String APP_PROPERTY_FILE = Paths.get (System.getProperty ("user.dir"), "src", "main",
            "resources", "application.properties")
        .toString ();

    private static final String TEST_DATA_PROPERTY_FILE = Paths.get (System.getProperty ("user.dir"), "src", "main",
                    "resources", "adminInputData.properties")
            .toString ();

    public static String getApplicationProps (final String environmentKey) {
        if (new File (APP_PROPERTY_FILE).exists ()) {
            return FileUtility.readPropertyFile (APP_PROPERTY_FILE)
                .get (environmentKey)
                .toString ();
        }
        throw new RuntimeException ("App properties file not exist at" + APP_PROPERTY_FILE);
    }

    public static String getAdminInputDataProps(final String environmentKey) {
        if (new File (TEST_DATA_PROPERTY_FILE).exists ()) {
            return FileUtility.readPropertyFile (TEST_DATA_PROPERTY_FILE)
                    .get (environmentKey)
                    .toString ();
        }
        throw new RuntimeException ("Test Data properties file not exist at" + TEST_DATA_PROPERTY_FILE);
    }
}




