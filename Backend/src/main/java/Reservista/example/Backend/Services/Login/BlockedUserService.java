package Reservista.example.Backend.Services.Login;

import Reservista.example.Backend.DAOs.BlockedUserRepository;
import Reservista.example.Backend.Models.BlockedUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BlockedUserService {

    @Autowired
    private BlockedUserRepository blockedUserRepository;

    public boolean isUserBlocked(String userNameOrEmail){
        BlockedUser blockedUser = blockedUserRepository
                .findById(userNameOrEmail) // first check by userName
                .orElse( // if not found by userName check with email
                        blockedUserRepository
                                .findByEmail(userNameOrEmail)
                                .orElse(null) // user is not found
                );

        return blockedUser != null;
    }

}
