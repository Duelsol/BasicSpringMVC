package me.duelsol.springmvcseed.framework.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import me.duelsol.springmvcseed.framework.util.PropertiesUtils;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by IntelliJ IDEA.
 * User: 冯奕骅
 * Date: 2017/5/16
 * Time: 14:16
 */
public class AccessTokenManager {

    private static String base64EncodedKey = null;

    private static final Logger LOGGER = LoggerFactory.getLogger(AccessTokenManager.class);

    static {
        String rowKey = PropertiesUtils.getProperty("jwt.key");
        base64EncodedKey = Base64.encodeBase64String(rowKey.getBytes());
    }

    private AccessTokenManager() {}

    public static String generate() {
        String subject = PropertiesUtils.getProperty("jwt.subject");
        return Jwts.builder().setSubject(subject).signWith(SignatureAlgorithm.HS256, base64EncodedKey).compact();
    }

    public static boolean validate(String token) {
        try {
            Claims claims = Jwts.parser().setSigningKey(base64EncodedKey).parseClaimsJws(token).getBody();
            if (claims != null) {
                String subject = PropertiesUtils.getProperty("jwt.subject");
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

}
