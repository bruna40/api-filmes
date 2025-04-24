package br.com.fillms.projeto_java_ia.providers;

import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.exceptions.TokenExpiredException;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Value;

@Service
public class JWTProvider {

     private static final Logger logger = LoggerFactory.getLogger(JWTProvider.class);
    @Value("${security.token.secret}")
    private String secretKey;
  
    public DecodedJWT validateToken(String token) {
      token = token.replace("Bearer ", "");
  
      Algorithm algorithm = Algorithm.HMAC256(secretKey);
  
      try {
        return JWT.require(algorithm)
            .build()
            .verify(token);
  
        
      } catch (TokenExpiredException ex) {
            logger.error("Expired token", ex);
        } catch (JWTVerificationException ex) {
            logger.error("Invalid token" , ex);
        } catch (Exception ex) {
            logger.error("Error validating token", ex);
        }
        return null;
    }

    public String extractId(String token) {
        DecodedJWT decodedToken = validateToken(token);
        if (decodedToken != null) {
            String id = decodedToken.getClaim("id").asString();
            logger.info("ID extraído do token: " + id); 
            return id;
        }
        return null;
    }
}
