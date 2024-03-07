package pl.jgmbl.yarnshop.security;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;

public class PasswordSecurity {
    public byte[] hashPassword (String password) throws NoSuchAlgorithmException, InvalidKeySpecException {
        SecureRandom random = new SecureRandom();

        byte[] salt = new byte[16];
        random.nextBytes(salt);

        KeySpec spec = new PBEKeySpec(password.toCharArray(), salt, 65536, 128);
        SecretKeyFactory factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");

        byte[] hash = factory.generateSecret(spec).getEncoded();

        byte[] saltAndHash = new byte[hash.length + salt.length];

        System.arraycopy(hash, 0, saltAndHash, 0, hash.length);
        System.arraycopy(salt, 0, saltAndHash, hash.length, salt.length);

        return saltAndHash;
    }
}
