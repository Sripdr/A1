package in.shop.service.impl;

import in.shop.repository.AuthRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import static in.shop.util.AuthConstants.NOT_FOUND;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {
    private final AuthRepository repository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        if (username.contains("@"))
            return repository.findByEmail(username).orElseThrow(() -> new UsernameNotFoundException(NOT_FOUND + username));
        else if (username.matches("\\d{10}"))
            return repository.findByPhoneNumber(username).orElseThrow(() -> new UsernameNotFoundException(NOT_FOUND + username));
    else
        return repository.findByEmailOrPhoneNumber(username, username).orElseThrow(() -> new UsernameNotFoundException(NOT_FOUND + username));
    }
}
