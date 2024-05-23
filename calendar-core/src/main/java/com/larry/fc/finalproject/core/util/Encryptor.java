package com.larry.fc.finalproject.core.util;

public interface Encryptor {
    String encrypt(String origin);

    boolean isMatch(String origin, String hashed);
}
