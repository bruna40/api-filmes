package br.com.fillms.projeto_java_ia.providers;

import java.time.Instant;

import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;

import java.time.Duration;
import java.util.Date;
import java.util.UUID;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Value;


@Service
public class JWTUserProvider {
    private static final Logger logger = LoggerFactory.getLogger(JWTProvider.class);
  @Value("${security.token.secret.user}")
  private String secretKey;

  public DecodedJWT validateToken(String token) {
    token = token.replace("Bearer ", "");

    Algorithm algorithm = Algorithm.HMAC256(secretKey);

    try {
      var tokenDecoded = JWT.require(algorithm)
          .build()
          .verify(token);

      return tokenDecoded;
    }  catch (JWTVerificationException e) {
        logger.error("Invalid or expired token", e);
        return null;
      } catch (Exception e) {
        logger.error("Error validating token", e);
        return null; 
      }
  }

  public String generateToken(UUID id, String email, String name, String role) {
    Algorithm algorithm = Algorithm.HMAC256(secretKey);

    Instant expiresIn = Instant.now().plus(Duration.ofDays(1));

    Date expirationDate = Date.from(expiresIn);

    return JWT.create()
        .withIssuer("java-ia")
        .withClaim("email", email)
        .withClaim("name", name)
        .withClaim("id", id.toString())
        .withClaim("role", role) 
        .withExpiresAt(expirationDate)
        .sign(algorithm);
}


  public String extractId(String token) {
      token = token.replace("Bearer ", "");

      Algorithm algorithm = Algorithm.HMAC256(secretKey);
      
      try {
          
          DecodedJWT decodedToken = JWT.require(algorithm)
              .build()
              .verify(token);
          String id = decodedToken.getClaim("id").asString();
          
          if (id != null) {
              
              return id;
          } else {
              logger.error("ID não encontrado no token");
          }
      } catch (JWTVerificationException e) {
          logger.error("Token inválido ou expirado", e);
      } catch (Exception e) {
          logger.error("Erro ao validar o token", e);
      }

      return null; 
  }

  public String extractIdFromToken(String token) {
    return this.extractId(token);
  }

    
}
