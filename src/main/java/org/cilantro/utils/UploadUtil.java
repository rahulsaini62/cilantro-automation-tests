package org.cilantro.utils;

import java.io.File;
import java.nio.file.Paths;

public final class UploadUtil {
    private static final String UPLOAD_FILE_DIR = Paths.get (System.getProperty ("user.dir"), "src", "main",
            "resources", "uploads")
        .toString ();

    public static String getUploadFilePath (final String fileName) {
        return UPLOAD_FILE_DIR + File.separator + fileName;
    }
}
