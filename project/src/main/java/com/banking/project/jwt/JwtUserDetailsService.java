package com.banking.project.jwt;

import com.banking.project.entity.BankUser;
import com.banking.project.repository.BankUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Set;

import static com.banking.project.constant.ExceptionMessages.USER_NOT_FOUND_MESSAGE;

@Service
@RequiredArgsConstructor
public class JwtUserDetailsService implements UserDetailsService {
    private final BankUserRepository bankUserRepository;
    @Override
    public UserDetails loadUserByUsername(final String username) throws UsernameNotFoundException {
        final BankUser user =
                bankUserRepository.findBankUserByEmail(username)
                .orElseThrow(()-> new UsernameNotFoundException(USER_NOT_FOUND_MESSAGE));

        final Set<SimpleGrantedAuthority> authorities = Set.of(new SimpleGrantedAuthority(user.getRole().name()));


        return new User(user.getEmail(), user.getPassword(), authorities);
    }
}
