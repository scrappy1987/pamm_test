package net.atos.pamm.infrastructure.security.authentication;

import io.jsonwebtoken.*;
import io.jsonwebtoken.impl.TextCodec;
import net.atos.pamm.domain.user.model.User;
import net.atos.pamm.domain.user.repository.UserRepository;
import net.atos.pamm.infrastructure.security.cipher.Cipher;
import net.atos.pamm.infrastructure.util.RandomKeyGenerator;
import org.apache.commons.lang3.time.DateUtils;
import play.Configuration;

import javax.inject.Inject;
import java.util.Date;

public class UserBearerDbAuthenticator implements Authenticator {
    private final Configuration configuration;
    private final Cipher cipher;
    private final RandomKeyGenerator randomKeyGenerator;
    private final UserRepository userRepository;

    @Inject
    public UserBearerDbAuthenticator(Configuration configuration,
                                     Cipher cipher,
                                     RandomKeyGenerator randomKeyGenerator,
                                     UserRepository userRepository) {
        this.configuration = configuration;
        this.cipher = cipher;
        this.randomKeyGenerator = randomKeyGenerator;
        this.userRepository = userRepository;
    }

    private String getSecretKey() {
        return configuration.getString("play.crypto.secret");
    }

    private String generateJwtTokenForUser(final User user) {
        final Date issueDate = new Date();

        return Jwts.builder()
                .setId(randomKeyGenerator.generate())
                .setSubject(user.getId().toString())
                .setIssuedAt(issueDate)
                .setExpiration(DateUtils.addHours(issueDate, 1))
                .signWith(SignatureAlgorithm.HS256, TextCodec.BASE64.encode(getSecretKey()))
                .claim("role", "user")
                .claim("id", user.getId().toString())
                .claim("forename", user.getForename())
                .claim("surname", user.getSurname())
                .claim("email", user.getEmail())
                .claim("phone", user.getPhone())
                .compact();
    }

    @Override
    public Principal validateToken(final String token) {
        try {
            final Claims claims = Jwts.parser()
                    .setSigningKey(TextCodec.BASE64.encode(getSecretKey()))
                    .parseClaimsJws(token).getBody();
            return new Principal(token, TokenStatus.VALID, claims);
        } catch (ExpiredJwtException expiredJwtException) {
            return new Principal(token, TokenStatus.EXPIRED);
        } catch (SignatureException signatureException) {
            return new Principal(token, TokenStatus.INVALID);
        }
    }

    @Override
    public Principal authenticate(final String username, final String plainPassword) {
        final User user = userRepository.findUserByEmail(username);
        if (user != null && user.isActivated()) {
            if (cipher.verify(plainPassword, user.getPassword())) {
                return validateToken(generateJwtTokenForUser(user));
            }
        }
        return null;
    }
}

