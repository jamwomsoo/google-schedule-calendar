package com.larry.fc.finalproject.core;

import com.larry.fc.finalproject.core.util.BcryptEncryptor;
import com.larry.fc.finalproject.core.util.Encryptor;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class BCryptEncryptorTest {
    @Test
    void bcryptTest(){
        final String origin = "my_password";
        final Encryptor encryptor = new BcryptEncryptor();
        final String hashed = encryptor.encrypt(origin);

        assertTrue(encryptor.isMatch(origin, hashed));

        final String wrong = "my_passwordd";
        assertFalse(encryptor.isMatch(wrong, hashed));
    }
}
