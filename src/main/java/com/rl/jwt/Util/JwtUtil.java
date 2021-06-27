package com.rl.jwt.Util;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import com.rl.jwt.Enum.TokenStatus;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

public class JwtUtil {
    /**
     * 加密秘钥
     */
    private static String secret = "a1g2y47dg3dj59fjhhsd7cnewy73j";


    /**
     * 生成token
     *
     * @param userId
     * @return String
     */
    public static String generateToken(Long userId, Long orgId, Integer type, String accountName) {
        Map<String, Object> claims = new HashMap<>(4);
        claims.put("userId", userId);
        claims.put("orgId", orgId);
        claims.put("type", type);
        claims.put("accountName", accountName);
        return generateToken(claims);
    }

    /**
     * 生成token
     *
     * @param claims
     * @return String
     */
    private static String generateToken(Map<String, Object> claims) {
        return Jwts.builder()
                .setClaims(claims)
                .setExpiration(generateExpirationDate())
                .setIssuedAt(generateCurrentDate())
                .signWith(SignatureAlgorithm.HS512, secret)
                .compact();
    }

    private static Date generateCurrentDate() {
        return new Date(System.currentTimeMillis());
    }


    private static Date generateExpirationDate() {
        /**
         * 有效时间(当前设置30天)
         */
        long expiration = (long) 30 * 24 * 60 * 60 * 1000;
        return new Date(System.currentTimeMillis() + expiration);
    }

    /**
     * 判断token是否可以刷新
     *
     * @param token
     * @param lastPasswordReset
     * @return Boolean
     */
    public static Boolean canTokenBeRefreshed(String token, Date lastPasswordReset) {
        Claims claims;
        try {
            claims = Jwts.parser()
                    .setSigningKey(secret)
                    .parseClaimsJws(token)
                    .getBody();
            final Date iat = claims.getIssuedAt();
            final Date exp = claims.getExpiration();
            if (iat.before(lastPasswordReset) || exp.before(generateCurrentDate())) {
                return false;
            }
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * 刷新token
     *
     * @param token
     * @return String
     */
    public static String refreshToken(String token) {
        String refreshedToken;
        try {
            final Claims claims = Jwts.parser()
                    .setSigningKey(secret)
                    .parseClaimsJws(token)
                    .getBody();
            refreshedToken = generateToken(claims);
        } catch (Exception e) {
            refreshedToken = null;
        }
        return refreshedToken;
    }

    /**
     * 校验token
     *
     * @param token
     * @return TokenStatus
     */
    public static TokenStatus verifyToken(String token) {
        TokenStatus result;
        Claims claims;
        try {
            claims = Jwts.parser()
                    .setSigningKey(secret)
                    .parseClaimsJws(token)
                    .getBody();
            final Date exp = claims.getExpiration();
            if (exp.before(generateCurrentDate())) {
                result = TokenStatus.EXPIRED;
            } else {
                result = TokenStatus.VALID;
            }
        } catch (Exception e) {
            result = TokenStatus.INVALID;
        }
        return result;
    }

    /**
     * 获取用户编号
     *
     * @param token
     * @return Integer
     */
    public static String getUserNameFromToken(String token) {
        String userName;
        Claims claims;
        try {
            claims = Jwts.parser()
                    .setSigningKey(secret)
                    .parseClaimsJws(token)
                    .getBody();
            userName = claims.getSubject();
        } catch (Exception e) {
            userName = null;
        }
        return userName;
    }


    /**
     * 获取用户编号
     *
     * @param token
     * @return Integer
     */
    public static Claims getClaims(String token) throws Exception {
        try {
            return Jwts.parser()
                    .setSigningKey(secret)
                    .parseClaimsJws(token)
                    .getBody();
        } catch (Exception e) {
            throw new Exception("无效的token");
        }
    }
}
