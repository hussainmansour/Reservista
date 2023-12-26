package Reservista.example.Backend.Services.Profile;

import Reservista.example.Backend.DAOs.UserRepository;
import Reservista.example.Backend.DTOs.Profile.ProfileDTO;
import Reservista.example.Backend.DTOs.Profile.UpdateDTO;
import Reservista.example.Backend.Enums.StatusCode;
import Reservista.example.Backend.Error.GlobalException;
import Reservista.example.Backend.Models.EmbeddedClasses.FullName;
import Reservista.example.Backend.Models.EntityClasses.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ProfileService {

    @Autowired
    private UserRepository userRepository;


    public ProfileDTO viewProfile(String username) throws GlobalException {

        User user = userRepository.findById(username).orElseThrow(()->new GlobalException(StatusCode.PROFILE_NOT_FOUND, HttpStatus.NOT_FOUND)); // 404
//        Optional<User> user = userRepository.findById(username);
        ProfileDTO profileDTO = ProfileDTO.builder()
                .userName(user.getUsername())
                .email(user.getEmail())
                .firstName(user.getFullName().getFirstName())
                .middleName(user.getFullName().getMiddleName())
                .lastName(user.getFullName().getLastName())
                .birthDate(user.getBirthDate())
                .gender(user.getGender())
                .nationality(user.getNationality())
                .build();

        return profileDTO;
    }

    public void updateProfile(String username, UpdateDTO updateDTO) throws GlobalException {
        User user = userRepository.findById(username).orElseThrow(()->new GlobalException(StatusCode.PROFILE_NOT_FOUND,HttpStatus.NOT_FOUND));
        user.setFullName(FullName.builder()
                .firstName(updateDTO.getFirstName())
                .middleName(updateDTO.getMiddleName())
                .lastName(updateDTO.getLastName())
                .build());
        user.setBirthDate(updateDTO.getBirthDate());
        user.setGender(updateDTO.getGender());
        user.setNationality(updateDTO.getNationality());
        userRepository.save(user);

    }

    //TODO: Add Reservations history
    //TODO: Add Upcoming reservations

}
