package io.liu.catnip;

import io.liu.catnip.Utils.JWTUtils;
import org.junit.jupiter.api.Test;


public class JWTUtilsTest {
    private final JWTUtils jwtUtils = new JWTUtils();

    public static final int TEST_ID = 123456789;


    @Test
    public void testJWTEncode(){
        System.out.println(jwtUtils.encode(TEST_ID));
    }

    @Test
    public void testVerify(){
        String token = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzUxMiJ9.eyJpc3MiOiJjYXRuaXAiLCJpZCI6IjIiLCJleHAiOjE2Njg3NDU1ODN9.GPkY0O3GcGUFdj2zEJmK_yQsG0sHqMpfEUM73-hOQDYiycqbeyHl89EaeK303n7iXw8HiuSNrs80HWxgxyhNoA";

        System.out.println(jwtUtils.verify(token));
    }
}
