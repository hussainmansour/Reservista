package Reservista.example.Backend.Services;


import Reservista.example.Backend.DAOs.BlockedUserRepository;
import Reservista.example.Backend.DAOs.UserRepository;
import Reservista.example.Backend.Models.User;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdTokenVerifier;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.gson.GsonFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.client.registration.ClientRegistration;
import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository;
import org.springframework.stereotype.Service;

import javax.security.auth.login.CredentialException;
import java.util.Collections;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class OAuthAuthenticationService {

    @Autowired
    private ClientRegistrationRepository clientRegistrationRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    DummyJWTService dummyJWTService;

    @Autowired
    private BlockedUserRepository blockedUserRepository;


    public String authenticateGoogleUser (String idTokenString) throws CredentialsException {

        // validate the passed idToken
        GoogleIdToken idToken = validateGoogleIdToken(idTokenString);

        // update the user in your system
        User user = manageUserAccount(idToken);

        // return user JWT
        return dummyJWTService.generateToken(user);

    }

    private User manageUserAccount(GoogleIdToken idToken) throws CredentialsException, CredentialException {

        GoogleIdToken.Payload payload = idToken.getPayload();

        // Get email from payload
        String email = payload.getEmail();

        if (!blockedUserRepository.existsByEmail(email))
            throw new CredentialsException("this account is blocked");

        if (!userRepository.existsByEmail(email)){

            // get profile information from payload
            String name = (String) payload.get("name");
            String pictureUrl = (String) payload.get("picture");
            String familyName = (String) payload.get("family_name");
            String firstName = (String) payload.get("firstName");

            User user =
                    User.builder()
                            .firstName(name)
                            .lastName(familyName)
                            .userName(email)
                            .email(email)
                            .isValidated(true)
                            .build();

            return userRepository.save(user);

        }

        // if account was already registered but not activated
        if (userRepository.existsByEmail(email) && userRepository.FindEnabledByEmail(email))
            userRepository.setIsValidatedBoolean(email);

        return userRepository.findByEmail(email).orElseThrow(()->new CredentialException("email doesn't exist"));


    }

    protected GoogleIdToken validateGoogleIdToken(String idTokenString) throws  CredentialsException {

        try {

            ClientRegistration clientRegistration = clientRegistrationRepository.findByRegistrationId("google");
            GsonFactory jsonFactory = GsonFactory.getDefaultInstance();
            HttpTransport transport = new NetHttpTransport();
            GoogleIdTokenVerifier verifier = new GoogleIdTokenVerifier.Builder(transport, jsonFactory)
                    .setAudience(Collections.singletonList(clientRegistration.getClientId()))
                    .build();

            GoogleIdToken idToken = verifier.verify(idTokenString);

            if (idToken == null)
                throw new CredentialsException("invalid google idToken");

            return idToken;

        }
        catch (Exception e) {
            throw new CredentialsException("couldn't validate idToken");
        }


    }
}
