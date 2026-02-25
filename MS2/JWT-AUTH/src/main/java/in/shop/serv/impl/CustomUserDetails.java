package in.shop.serv.impl;

import in.shop.excep.UserNotFound;
import in.shop.repo.AuthRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class CustomUserDetails implements  UserDetailsService {

    private final AuthRepository authRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        log.info("Loading user by username: {}", username);
        return authRepository.findByUsername(username).orElseThrow(()->
                new UserNotFound("User not found with Given Username :"+username));
    }


}
