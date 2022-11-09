package io.liu.catnip;

import io.liu.catnip.Utils.JWTUtils;
import org.junit.jupiter.api.Test;


public class JWTUtilsTest {
    private final JWTUtils jwtUtils = new JWTUtils();

    public static final int TEST_ID = 123456789;
    @Test
    public void testJETEncode(){
        System.out.println(jwtUtils.encode(TEST_ID));
    }
}
