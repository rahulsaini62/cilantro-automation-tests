package org.cilantro.utils;

import java.util.Base64;

public final class Base64EncryptionUtil {

    public static String getBase64EncryptedString (final String inputStr) {
        return Base64.getEncoder ()
            .encodeToString (inputStr.getBytes ());
    }

    public static String getBase64DecryptedString (final String encodedString) {
        final byte[] decodedBytes = Base64.getDecoder ()
            .decode (encodedString);
        return new String (decodedBytes);
    }

    public static void main (final String[] args) {
        final Base64EncryptionUtil base64EncryptionUtil = new Base64EncryptionUtil ();
        final String inputString = "c8daede3-40ec-41fe-ab4d-1754b6ffa2f3:bmKxYydcZDDcwQJEGgeE4HAxPda5u55U3ni1vlrA9xg=";
        final String encryptedString = getBase64EncryptedString (inputString);
        System.out.println (encryptedString);
    }
}
