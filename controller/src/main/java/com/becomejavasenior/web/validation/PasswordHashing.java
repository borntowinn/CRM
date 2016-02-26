package com.becomejavasenior.web.validation;

/**
 * Created by sergiig on 29/02/16.
 */

import com.becomejavasenior.User;
import org.apache.log4j.Logger;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

public class PasswordHashing {

    private static final Logger log = Logger.getLogger(PasswordHashing.class);
    public String SALT = generateSalt();

    public String getSALT() {
        return SALT;
    }

    public String hashPassword(String password) {
        String saltedPassword = SALT + password;
        String hashedPassword = generateHash(saltedPassword);
        return hashedPassword;
    }

    /**
     * Validate password with password from database
     *
     * @param user, password user,password for validation
     * @return true valid password, false invalid password
     */
    public Boolean login(User user, String password) {

        String saltedPassword = user.getPasswordSalt() + password;
        String hashedPassword = generateHash(saltedPassword);
        String passwordFromDB = user.getPassword();
        return hashedPassword.equals(passwordFromDB);
    }

    public String generateHash(String input) {
        StringBuilder hash = new StringBuilder();

        try {
            MessageDigest sha = MessageDigest.getInstance("SHA-1");
            byte[] hashedBytes = sha.digest(input.getBytes());
            char[] digits = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
                    'a', 'b', 'c', 'd', 'e', 'f'};
            for (int idx = 0; idx < hashedBytes.length; ++idx) {
                byte b = hashedBytes[idx];
                hash.append(digits[(b & 0xf0) >> 4]);
                hash.append(digits[b & 0x0f]);
            }
        } catch (NoSuchAlgorithmException e) {
            log.warn(e + "PasswordHashing.java No SHA-1 algorithm was found!");
        }

        return hash.toString();
    }

    private String generateSalt() {
        int value;
        SecureRandom random;
        try {
            random = SecureRandom.getInstance("SHA1PRNG");
            value = random.nextInt();

        } catch (NoSuchAlgorithmException e) {

            sun.security.provider.SecureRandom r = new sun.security.provider.SecureRandom();
            byte[] b = new byte[4];
            r.engineNextBytes(b);
            value = ((0xFF & b[0]) << 24) | ((0xFF & b[1]) << 16) |
                    ((0xFF & b[2]) << 8) | (0xFF & b[3]);
            log.warn(e + "Using a newly created SecureRandom object to generate salt: SHA1PRNG instance of SecureRandom was not found!");

        }
        value = Math.abs(value);
        return Integer.toString(value);
    }

}