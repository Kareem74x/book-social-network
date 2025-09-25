package com.kareem.book_network.security;

import com.kareem.book_network.user.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    @Transactional // when I load the user i want also to load roles with it
    public UserDetails loadUserByUsername(String userEmail) throws UsernameNotFoundException {

        // pass email because it's the unique identifier of the user in the app
        return userRepository.findByEmail(userEmail)
                .orElseThrow(() -> new UsernameNotFoundException("User not found with Email: " + userEmail));
    }
}
