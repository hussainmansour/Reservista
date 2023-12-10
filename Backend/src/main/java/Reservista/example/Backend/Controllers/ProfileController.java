package Reservista.example.Backend.Controllers;

import Reservista.example.Backend.DTOs.Profile.ProfileDTO;
import Reservista.example.Backend.DTOs.Profile.UpdateDTO;
import Reservista.example.Backend.DTOs.Response.ResponseDTO;
import Reservista.example.Backend.Services.Profile.ProfileService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/user/profile")
public class ProfileController {

    @Autowired
    private ProfileService profileService;


    @GetMapping("/view")
    public ResponseEntity<ResponseDTO<ProfileDTO>> viewProfile(@AuthenticationPrincipal String username) {
        Optional<ProfileDTO> profile = profileService.viewProfile(username);
        return profile.map(profileDTO -> new ResponseEntity<>(
                ResponseDTO.<ProfileDTO>builder()
                        .status(HttpStatus.OK.value())
                        .message("Profile found")
                        .data(profileDTO)
                        .build(), HttpStatus.OK)).orElse(new ResponseEntity<>(
                ResponseDTO.<ProfileDTO>builder()
                        .status(HttpStatus.NOT_FOUND.value())
                        .message("Profile not found")
                        .build(), HttpStatus.NOT_FOUND));
    }

    @PutMapping("/edit")
    public ResponseEntity<ResponseDTO<Void>> editProfile(@AuthenticationPrincipal String username, @Valid @RequestBody UpdateDTO updateDTO) {
        Optional<UpdateDTO> update = profileService.updateProfile(username,updateDTO);
        return update.map(updateDTO1 -> new ResponseEntity<>(
                ResponseDTO.<Void>builder()
                        .status(HttpStatus.OK.value())
                        .message("Profile updated")
                        .build(), HttpStatus.OK)).orElse(new ResponseEntity<>(
                ResponseDTO.<Void>builder()
                        .status(HttpStatus.NOT_FOUND.value())
                        .message("Profile not found")
                        .build(), HttpStatus.NOT_FOUND));
    }

}
