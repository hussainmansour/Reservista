package Reservista.example.Backend.Controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OAuthAuthenticationController {


    @PostMapping("/validate-google-id-new")
    public ResponseEntity<String> validateAndSaveUserr(@RequestParam("idToken") String idTokenString) throws CredentialsException {

        return ResponseEntity.ok(oAuthAuthenticationService.authenticateGoogleUser(idTokenString));


    }

}
