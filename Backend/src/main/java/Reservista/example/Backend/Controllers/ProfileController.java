package Reservista.example.Backend.Controllers;

import Reservista.example.Backend.DTOs.Profile.ProfileDTO;
import Reservista.example.Backend.Services.Profile.ProfileService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/user/profile")
public class ProfileController {

    @Autowired
    private ProfileService profileService;


    @GetMapping("/view/{username}")
    public ResponseEntity<ProfileDTO> viewProfile(@PathVariable("username") String username) {
        Optional<ProfileDTO> user = profileService.viewProfile(username);
        return user.map(profileDTO -> new ResponseEntity<>(profileDTO, HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PutMapping("/edit")
    public ResponseEntity<ProfileDTO> editProfile( @Valid @RequestBody ProfileDTO profileDTO) {
        Optional<ProfileDTO> user = profileService.updateProfile(profileDTO);
        return user.map(profileDTO1 -> new ResponseEntity<>(profileDTO1, HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

}
