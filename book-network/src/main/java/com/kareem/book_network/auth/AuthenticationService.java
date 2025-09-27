package com.kareem.book_network.auth;

import com.kareem.book_network.role.RoleRepository;
import com.kareem.book_network.user.Token;
import com.kareem.book_network.user.TokenRepository;
import com.kareem.book_network.user.User;
import com.kareem.book_network.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    private final TokenRepository tokenRepository;


    public void register(RegistrationRequest request) {

        var userRoles = roleRepository.findByName("USER")
                // to do - better exception handling
                .orElseThrow(() -> new IllegalStateException("ROLE NOT FOUND"));

        var user = User.builder()
                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .accountLocked(false)
                .enabled(false)
                .roles(List.of(userRoles))
                .build();

        userRepository.save(user);
        sendValidationEmail(user);
    }







    private String generateActivationCode(int length) {

        String chars = "0123456789";
        StringBuilder codeBuilder = new StringBuilder();
        SecureRandom secureRandom = new SecureRandom();

        for(int i = 0; i < length; i++) {
            int randomIndex = secureRandom.nextInt(chars.length()); // 0..9
            codeBuilder.append(chars.charAt(randomIndex));
        }

        return codeBuilder.toString();
    }

    private String generateAndSaveActivationToken(User user) {

        // generate token
        String generatedToken = generateActivationCode(6);


        var token = Token.builder()
                .token(generatedToken)
                .createdAt(LocalDateTime.now())
                .expiresAt(LocalDateTime.now().plusMinutes(15))
                .user(user)
                .build();


        tokenRepository.save(token);
        return generatedToken;
    }

    private void sendValidationEmail(User user) {

        var newToken = generateAndSaveActivationToken(user);
        
        // send email
    }

}
