package me.duelsol.springmvcseed.service.token;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import me.duelsol.springmvcseed.framework.token.TokenManagerDelegate;
import me.duelsol.springmvcseed.framework.util.PropertiesUtils;
import me.duelsol.springmvcseed.service.BaseService;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;

/**
 * Created by IntelliJ IDEA.
 * User: 冯奕骅
 * Date: 2017/5/16
 * Time: 14:16
 */
@Service
public class JWTAdapter extends BaseService implements TokenManagerDelegate {

    private static String base64EncodedKey = null;

    private static final Logger LOGGER = LoggerFactory.getLogger(JWTAdapter.class);

    static {
        String rowKey = PropertiesUtils.getProperty("jwt.key");
        base64EncodedKey = Base64.encodeBase64String(rowKey.getBytes());
    }

    @Override
    public String generate() {
        String subject = PropertiesUtils.getProperty("jwt.subject");
        return Jwts.builder().setSubject(subject).signWith(SignatureAlgorithm.HS256, base64EncodedKey).compact();
    }

    @Override
    public boolean validate(String token) {
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

    @Override
    public void put(String token, Object target) {
        if (target instanceof HttpServletResponse) {
            try (OutputStream outputStream = ((HttpServletResponse) target).getOutputStream()) {
                outputStream.write(token.getBytes());
                outputStream.flush();
            } catch (IOException e) {
                LOGGER.error("JWT写入response时发生错误，token=" + token, e);
            }
        }
    }

    @Override
    public String get(Object target) {
        if (target instanceof HttpServletRequest) {
            String authorization = ((HttpServletRequest) target).getHeader("Authorization");
            if (StringUtils.isNotBlank(authorization) && authorization.startsWith("Bearer ")) {
                return authorization.substring("Bearer ".length());
            }
        }
        return null;
    }

    @Override
    public void remove(String token, Object target) {
        // JWT实现方式下的remove，这里可以执行将token加入黑名单保存到Redis等操作。
    }

}
