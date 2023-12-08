package Reservista.example.Backend.Config;

import Reservista.example.Backend.DAOs.AdminRepository;
import Reservista.example.Backend.Models.EntityClasses.Admin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class AdminLoader implements CommandLineRunner {

    @Value("${main.admin.username}")
    private String adminName;

    @Value("${main.admin.password}")
    private String password;

    @Autowired
    private AdminRepository adminRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) throws Exception {
        if (!adminRepository.existsByAdminName(adminName)) {
            Admin admin = Admin.builder()
                    .adminName(adminName)
                    .password(passwordEncoder.encode(password))
                    .build();

            adminRepository.save(admin);
        }
    }
}
