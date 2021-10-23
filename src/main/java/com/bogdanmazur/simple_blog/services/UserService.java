package com.bogdanmazur.simple_blog.services;

import com.bogdanmazur.simple_blog.dto.UserRegistrationDTO;
import com.bogdanmazur.simple_blog.entity.Role;
import com.bogdanmazur.simple_blog.entity.User;
import com.bogdanmazur.simple_blog.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Collection;
import java.util.stream.Collectors;

@Service
public class UserService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

/*We use encoder to save encoded password to DB*/
    public User save(UserRegistrationDTO registrationDTO) {
        User user = new User(registrationDTO.getFirstName(),
                registrationDTO.getLastName(),
                registrationDTO.getEmail(),
                passwordEncoder.encode(registrationDTO.getPassword()),
                Arrays.asList(new Role("USER")));

        return userRepository.save(user);
    }

    /*This class implements UserDetailsService to be able to connect to DaoAuthenticationProvider.
    So it has to override this method*/
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        /*Retrieve our User with userRepository*/
        User user = userRepository.findByEmail(username);
        if (user == null) {
            throw new UsernameNotFoundException("Invalid username or password");
        }

        /*This User class is a Spring class. As constructor parameters it accepts: username, password & roles
        Collection
        User implements UserDetails*/
        return new org.springframework.security.core.userdetails.User(user.getEmail(),
                user.getPassword(),
                mapRolesToAuthorities(user.getRoles()));
    }

    /*This method maps ROLES to Authorities*/
    private Collection<? extends GrantedAuthority> mapRolesToAuthorities(Collection<Role> roles) {
        return roles.stream()
                .map(role -> new SimpleGrantedAuthority(role.getName()))
                .collect(Collectors.toList());
    }
}
