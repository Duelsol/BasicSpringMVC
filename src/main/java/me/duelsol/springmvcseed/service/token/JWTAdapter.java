package me.duelsol.springmvcseed.service.token;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import me.duelsol.springmvcseed.framework.token.TokenManagerDelegate;
import me.duelsol.springmvcseed.framework.util.PropertiesUtils;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by IntelliJ IDEA.
 * User: 冯奕骅
 * Date: 2017/5/16
 * Time: 14:16
 */
public class JWTAdapter implements TokenManagerDelegate {

    private static String base64EncodedKey = null;

    private static final Logger LOGGER = LoggerFactory.getLogger(JWTAdapter.class);

    static {
        String rowKey = PropertiesUtils.getProperty("jwt.key");
        base64EncodedKey = Base64.encodeBase64String(rowKey.getBytes());
    }

    @Override
    public boolean check(HttpServletRequest request, HttpServletResponse response) {
        String authorization = request.getHeader("Authorization");
        if (StringUtils.isNotBlank(authorization) && authorization.startsWith("Bearer ")) {
            String token = authorization.substring("Bearer ".length());
            try {
                Claims claims = parse(token);
                if (claims != null) {
                    String subject = PropertiesUtils.getProperty("jwt.subject");
                    if (claims.getSubject().equals(subject)) {
                        return true;
                    }
                }
            } catch (RuntimeException e) {
                LOGGER.error("验证JWT时发生错误，token=" + token, e);
            }
        }
        return false;
    }

    @Override
    public void create(HttpServletRequest request, HttpServletResponse response) {
        String token = generate();
        try {
            response.getWriter().write(token);
        } catch (IOException e) {
            LOGGER.error("response写入JWT时发生错误，token=" + token, e);
        }
    }

    @Override
    public void remove(HttpServletRequest request, HttpServletResponse response) {
        // JWT实现方式下的remove，这里可以执行将token加入黑名单保存到Redis等操作。
    }

    private String generate() {
        String subject = PropertiesUtils.getProperty("jwt.subject");
        return Jwts.builder().setSubject(subject).signWith(SignatureAlgorithm.HS256, base64EncodedKey).compact();
    }

    private Claims parse(String claimsJws) {
        return Jwts.parser().setSigningKey(base64EncodedKey).parseClaimsJws(claimsJws).getBody();
    }

}
