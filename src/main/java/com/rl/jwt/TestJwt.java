package com.rl.jwt;

import com.rl.jwt.Util.JwtUtil;
import io.jsonwebtoken.Claims;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

public class TestJwt {
    @Test
    public void TestCreateJwt() throws Exception {
        String token = JwtUtil.generateToken(12345L, 78945L, 1, "accountName");
        System.out.println(token);

        Claims claims = JwtUtil.getClaims(token);

        System.out.println(JwtUtil.getUserNameFromToken(token));

    }

}
