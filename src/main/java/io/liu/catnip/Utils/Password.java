package io.liu.catnip.Utils;

import org.apache.commons.codec.binary.Hex;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

public class Password {
    private final static String ALGORITHM = "HmacSHA512";

    public static String encrypt(String message, String key) {

        byte[] messageBytes = message.getBytes(StandardCharsets.UTF_8);
        byte[] keyBytes = key.getBytes(StandardCharsets.UTF_8);

        String result = null;
        try {
            Mac mac = Mac.getInstance(ALGORITHM);
            SecretKeySpec secretKeySpec = new SecretKeySpec(keyBytes, ALGORITHM);
            mac.init(secretKeySpec);
            result = Hex.encodeHexString(mac.doFinal(messageBytes));
        } catch (NoSuchAlgorithmException | InvalidKeyException | NullPointerException e) {
            e.printStackTrace();
        }

        return result;
    }
}
