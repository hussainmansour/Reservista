package Reservista.example.Backend.Controllers;

import Reservista.example.Backend.DTOs.Profile.ProfileDTO;
import Reservista.example.Backend.DTOs.Profile.UpcomingHistoryReservationDTO;
import Reservista.example.Backend.DTOs.Profile.UpdateDTO;
import Reservista.example.Backend.Error.GlobalException;
import Reservista.example.Backend.Services.Profile.ProfileService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user/profile")
public class ProfileController {

    @Autowired
    private ProfileService profileService;


    @GetMapping("/view")
    public ResponseEntity<ProfileDTO> viewProfile(@AuthenticationPrincipal String username) throws GlobalException {
        ProfileDTO profile = profileService.viewProfile(username);
        return ResponseEntity.ok(profile);
//        return profile.map(profileDTO -> new ResponseEntity<>(
//               profileDTO, HttpStatus.OK)).orElse(new ResponseEntity<>(
//                ResponseDTO.<ProfileDTO>builder()
//                        .status(HttpStatus.NOT_FOUND.value())
//                        .message("Profile not found")
//                        .build(), HttpStatus.NOT_FOUND));
    }

    @PutMapping("/edit")
    public ResponseEntity<Void> editProfile(@AuthenticationPrincipal String username, @Valid @RequestBody UpdateDTO updateDTO) throws GlobalException {
        profileService.updateProfile(username,updateDTO);
        return ResponseEntity.status(HttpStatus.OK).build();
//        if(found){
//            return new ResponseEntity<>(
//                    ResponseDTO.<Void>builder()
//                            .status(HttpStatus.OK.value())
//                            .message("Profile updated")
//                            .build(), HttpStatus.OK);
//        }
//        else{
//            return new ResponseEntity<>(
//                    ResponseDTO.<Void>builder()
//                            .status(HttpStatus.NOT_FOUND.value())
//                            .message("Profile not found")
//                            .build(), HttpStatus.NOT_FOUND);
//        }
    }

    @GetMapping("/upcoming")
    public ResponseEntity<List<UpcomingHistoryReservationDTO>> getUpcomingReservations(@AuthenticationPrincipal String username) throws GlobalException {
        List<UpcomingHistoryReservationDTO> reservations = profileService.getUpcomingReservations(username);
        return ResponseEntity.ok(reservations);

    }

    @GetMapping("/history")
    public ResponseEntity<List<UpcomingHistoryReservationDTO>> getHistoryReservations(@AuthenticationPrincipal String username) throws GlobalException {
        List<UpcomingHistoryReservationDTO> reservations = profileService.getHistoryReservations(username);
        return ResponseEntity.ok(reservations);

    }

}
