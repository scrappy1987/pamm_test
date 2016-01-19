package net.atos.pamm.infrastructure.security.authentication;

import io.jsonwebtoken.Claims;

public class Principal {
    private final String authToken;
    private final Claims claims;
    private final TokenStatus tokenStatus;
    private final String subject;

    public Principal(String authToken, TokenStatus tokenStatus, Claims claims) {
        this.authToken = authToken;
        this.tokenStatus = tokenStatus;
        this.claims = claims;
        this.subject = claims.getSubject();
    }

    public Principal(String authToken, TokenStatus tokenStatus) {
        this.authToken = authToken;
        this.tokenStatus = tokenStatus;
        this.claims = null;
        this.subject = null;
    }

    public String getAuthToken() {
        return authToken;
    }

    public Claims getClaims() {
        return claims;
    }

    public TokenStatus getTokenStatus() {
        return tokenStatus;
    }

    public String getSubject() {
        return subject;
    }
}

