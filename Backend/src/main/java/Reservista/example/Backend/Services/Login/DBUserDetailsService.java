package Reservista.example.Backend.Services.Login;

import Reservista.example.Backend.DAOs.AdminRepository;
import Reservista.example.Backend.DAOs.UserRepository;
import Reservista.example.Backend.Models.EntityClasses.Admin;
import Reservista.example.Backend.Models.EntityClasses.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class DBUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AdminRepository adminRepository;

    @Autowired
    private BlockedUserService blockedUserService;

    @Override
    public UserDetails loadUserByUsername(String userNameOrEmail) throws UsernameNotFoundException {

        User user = userRepository
                .findById(userNameOrEmail) // first check by userName
                .orElse( // if not found by userName check with email
                        userRepository
                                .findByEmail(userNameOrEmail)
                                .orElse(null) // user is not found
                );


        Admin admin = adminRepository
                .findById(userNameOrEmail) // admin only has a userName(adminName) so we check once
                .orElse(null); // admin is not found


        if (user == null && admin == null)
            throw new UsernameNotFoundException(
                    String.format("User %s not found", userNameOrEmail)
            );

        return user == null ? admin : user;
    }
}
