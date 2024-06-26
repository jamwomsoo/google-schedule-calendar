package com.larry.fc.finalproject.core.util;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.stereotype.Component;

@Component
public class BcryptEncryptor implements Encryptor{
    @Override
    public String encrypt(String origin) {
        return BCrypt.hashpw(origin, BCrypt.gensalt());
    }

    @Override
    public boolean isMatch(String origin, String hashed) {
        try{
            return BCrypt.checkpw(origin, hashed);
        } catch(Exception e){
            return false;
        }
    }
}
