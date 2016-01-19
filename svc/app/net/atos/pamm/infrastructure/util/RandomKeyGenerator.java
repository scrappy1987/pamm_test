package net.atos.pamm.infrastructure.util;

import com.google.inject.Singleton;

import java.math.BigInteger;
import java.security.SecureRandom;

@Singleton
public class RandomKeyGenerator {
    private SecureRandom random = new SecureRandom();

    public String generate() {
        return new BigInteger(130, random).toString(32);
    }
}
