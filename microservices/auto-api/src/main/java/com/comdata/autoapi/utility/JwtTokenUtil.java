package com.comdata.autoapi.utility;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import com.comdata.autoservice.model.User;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.SignatureException;
import io.jsonwebtoken.UnsupportedJwtException;

@Component
public class JwtTokenUtil {
	
	private Logger logger = LoggerFactory.getLogger(JwtTokenUtil.class);
	
	private static final long EXPIRE_DURATION = 24 * 60 * 60 * 1000; //24h
	
	@Value("${app.jwt.secret}")
	private String secretKey;
	
	public String generateAccessToken(User user) {
		return Jwts.builder()
				.setSubject(user.getId() + "," + user.getEmail())
				.setIssuer("ComData")
				.setIssuedAt(new Date())
				.setExpiration(new Date(System.currentTimeMillis() + EXPIRE_DURATION))
				.signWith(SignatureAlgorithm.HS512, secretKey)
				.compact();
	}
	
	public boolean validateAccessToken(String token) {
        try {
            Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token);
            return true;
        } catch (ExpiredJwtException ex) {
        	logger.error("JWT expired", ex.getMessage());
        } catch (IllegalArgumentException ex) {
        	logger.error("Token is null, empty or only whitespace", ex.getMessage());
        } catch (MalformedJwtException ex) {
        	logger.error("JWT is invalid", ex);
        } catch (UnsupportedJwtException ex) {
        	logger.error("JWT is not supported", ex);
        } catch (SignatureException ex) {
        	logger.error("Signature validation failed");
        }
         
        return false;
    }
     
    public String getSubject(String token) {
        return parseClaims(token).getSubject();
    }
     
    private Claims parseClaims(String token) {
        return Jwts.parser()
                .setSigningKey(secretKey)
                .parseClaimsJws(token)
                .getBody();
    }
}
