package Reservista.example.Backend.Controllers;

import Reservista.example.Backend.DTOs.Profile.ProfileDTO;
import Reservista.example.Backend.DTOs.Profile.UpdateDTO;
import Reservista.example.Backend.Error.GlobalException;
import Reservista.example.Backend.Services.Profile.ProfileService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;



@RestController
@RequestMapping("/user/profile")
public class ProfileController {

    @Autowired
    private ProfileService profileService;


    @GetMapping("/view")
    public ResponseEntity<ProfileDTO> viewProfile(@AuthenticationPrincipal String username) throws GlobalException {
        ProfileDTO profile = profileService.viewProfile(username);
        return ResponseEntity.ok(profile);
}

    @PutMapping("/edit")
    public ResponseEntity<Void> editProfile(@AuthenticationPrincipal String username, @Valid @RequestBody UpdateDTO updateDTO) throws GlobalException {
        profileService.updateProfile(username,updateDTO);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

}
