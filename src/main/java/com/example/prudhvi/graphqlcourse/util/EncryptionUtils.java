package com.example.prudhvi.graphqlcourse.util;

import org.bouncycastle.crypto.generators.OpenBSDBCrypt;

import java.nio.charset.StandardCharsets;

public class EncryptionUtils {

    public static boolean isBcryptMatch(String original, String hashValue) {
        return OpenBSDBCrypt.checkPassword(hashValue, original.getBytes(StandardCharsets.UTF_8));
    }
}
