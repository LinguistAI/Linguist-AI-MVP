package app.linguistai.security;

import java.util.Collections;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import app.linguistai.model.User;
import app.linguistai.repository.IAccountRepository;

import org.springframework.security.core.userdetails.UserDetailsService;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class JWTUserService implements UserDetailsService {
    private final IAccountRepository accountRepository;

    @Override
    // @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        try {
            User appUser = accountRepository.findUserByEmail(username).orElse(null);
        
            if (appUser != null) {
                UserDetails userDetails = new org.springframework.security.core.userdetails.User(
                    username,
                    appUser.getPassword(),
                    Collections.singleton(new SimpleGrantedAuthority("USER"))
                );
                return userDetails;
            } else {
                throw new UsernameNotFoundException("User not found with email: " + username);
            }
        } catch (Exception e) {
            throw new RuntimeException("An error occurred while loading user details.", e);
        }
    }

    // @Override
    public UserDetails loadUserByUsername(User appUser) throws UsernameNotFoundException {
        try {
        
            UserDetails userDetails = new org.springframework.security.core.userdetails.User(
                                        appUser.getEmail(), 
                                        appUser.getPassword()
                                        // Collections.singleton(new SimpleGrantedAuthority(appUser.getUserType().toString()))
                                        , null
                                        );
            return userDetails; 
        } catch (Exception e) {
            throw e;
        }
    }    
}
