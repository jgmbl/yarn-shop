package pl.jgmbl.yarnshop;

import org.springframework.stereotype.Service;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;
import java.util.Arrays;


@Service
public class HashPasswordService {

    public HashPasswordService() {
    }

    public byte[] hashPassword(String password) throws NoSuchAlgorithmException, InvalidKeySpecException {
        SecureRandom random = new SecureRandom();

        byte[] salt = new byte[16];
        random.nextBytes(salt);

        byte[] hash = hashAlgorithm(salt, password);

        byte[] saltAndHash = new byte[hash.length + salt.length];

        System.arraycopy(hash, 0, saltAndHash, 0, hash.length);
        System.arraycopy(salt, 0, saltAndHash, hash.length, salt.length);

        return saltAndHash;
    }

    public boolean checkHashedPasswords(String notHashedPassword, byte[] hashedPassword) throws NoSuchAlgorithmException, InvalidKeySpecException {
        byte[] salt = Arrays.copyOfRange(hashedPassword, hashedPassword.length - 16, hashedPassword.length);

        byte[] hash = hashAlgorithm(salt, notHashedPassword);

        return Arrays.equals(hash, Arrays.copyOfRange(hashedPassword, 0, hashedPassword.length - 16));
    }

    private static byte[] hashAlgorithm (byte[] salt, String password) throws NoSuchAlgorithmException, InvalidKeySpecException {
        KeySpec spec = new PBEKeySpec(password.toCharArray(), salt, 65536, 128);
        SecretKeyFactory factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");

        return factory.generateSecret(spec).getEncoded();
    }
}
