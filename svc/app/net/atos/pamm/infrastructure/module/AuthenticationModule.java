package net.atos.pamm.infrastructure.module;

import com.google.inject.AbstractModule;
import net.atos.pamm.infrastructure.security.authentication.Authenticator;
import net.atos.pamm.infrastructure.security.authentication.UserBearerDbAuthenticator;
import net.atos.pamm.infrastructure.security.cipher.BCryptCipher;
import net.atos.pamm.infrastructure.security.cipher.Cipher;

public class AuthenticationModule extends AbstractModule {
    protected void configure() {

        bind(Authenticator.class).to(UserBearerDbAuthenticator.class);
        bind(Cipher.class).to(BCryptCipher.class);
    }
}
