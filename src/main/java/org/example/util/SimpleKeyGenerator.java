package org.example.util;

import jakarta.enterprise.context.ApplicationScoped;

import javax.crypto.spec.SecretKeySpec;
import java.security.Key;

@ApplicationScoped
public class SimpleKeyGenerator implements KeyGenerator {

    @Override
    public Key generateKey() {
        byte[] key = "ikmoetgewooneenlangerekeyerinflikkerenikmoetgewooneenlangerekeyerinflikkeren".getBytes();
        return new SecretKeySpec(key, 0, key.length, "HMACSHA256");
    }
}
