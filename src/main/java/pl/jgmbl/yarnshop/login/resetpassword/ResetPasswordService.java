package pl.jgmbl.yarnshop.login.resetpassword;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.jgmbl.yarnshop.HashPasswordService;
import pl.jgmbl.yarnshop.user.User;
import pl.jgmbl.yarnshop.user.UserRepository;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.Optional;

@Service
public class ResetPasswordService {
    @Autowired
    UserRepository userRepository;

    @Autowired
    HashPasswordService hashPasswordService;

    public boolean doesUserExist (String email) {
        Optional<User> findByEmail = userRepository.findByEmail(email.toLowerCase());

        return findByEmail.isPresent();
    }

    public boolean checkPasswords (String newPassword, String confirmPassword) {
        return PasswordValidator.validateSpecialCharacters(newPassword) && PasswordValidator.validateLength(newPassword) &&
                PasswordValidator.comparePasswords(newPassword, confirmPassword);
    }

    public Optional<User> updatePassword(ResetForm resetForm, String email, String password) {
        return userRepository.findByEmail(email)
                .map(existingUser -> {
                    try {
                        byte[] hashedPassword = hashPasswordService.hashPassword(password);

                        existingUser.setEmail(email);
                        existingUser.setPassword(hashedPassword);

                        return userRepository.save(existingUser);
                    } catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
                        throw new RuntimeException(e);
                    }
                });
    }
}
