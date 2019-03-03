package me.duelsol.springmvcseed.framework.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by IntelliJ IDEA.
 * User: 冯奕骅
 * Date: 2017/5/16
 * Time: 14:16
 */
@Component
public class AccessTokenManager {

    private static String subject;

    private static String base64EncodedKey;

    private static final Logger LOGGER = LoggerFactory.getLogger(AccessTokenManager.class);

    private AccessTokenManager() {}

    public static String generate() {
        return Jwts.builder().setSubject(subject).signWith(SignatureAlgorithm.HS256, base64EncodedKey).compact();
    }

    public static boolean validate(String token) {
        try {
            Claims claims = Jwts.parser().setSigningKey(base64EncodedKey).parseClaimsJws(token).getBody();
            if (claims != null) {
                if (claims.getSubject().equals(subject)) {
                    return true;
                }
            }
        } catch (RuntimeException e) {
            LOGGER.error("验证JWT时发生错误，token=" + token, e);
        }
        return false;
    }

    public static String get(HttpServletRequest request) {
        String authorization = request.getHeader("Authorization");
        if (StringUtils.isNotBlank(authorization) && authorization.startsWith("Bearer ")) {
            return authorization.substring("Bearer ".length());
        }
        return null;
    }

    @Value("${jwt.subject}")
    private void setSubject(String subject) {
        AccessTokenManager.subject = subject;
    }

    @Value("${jwt.key}")
    private void setKey(String key) {
        AccessTokenManager.base64EncodedKey = Base64.encodeBase64String(key.getBytes());
    }

}
