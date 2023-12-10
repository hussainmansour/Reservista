package Reservista.example.Backend.Services.Profile;

import Reservista.example.Backend.DAOs.UserRepository;
import Reservista.example.Backend.DTOs.Profile.ProfileDTO;
import Reservista.example.Backend.DTOs.Profile.UpdateDTO;
import Reservista.example.Backend.Models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ProfileService {

    @Autowired
    private UserRepository userRepository;


    public Optional<ProfileDTO> viewProfile(String username) {
        Optional<User> user = userRepository.findById(username);
        Optional<ProfileDTO> profileDTO;
        profileDTO = user.map(value -> ProfileDTO.builder().
                userName(value.getUsername()).
                email(value.getEmail()).
                firstName(value.getFirstName()).
                middleName(value.getMiddleName()).
                lastName(value.getLastName()).
                birthDate(value.getBirthDate()).
                gender(value.getGender()).
                nationality(value.getNationality()).
                build());
        return profileDTO;
    }

    public boolean updateProfile(String username, UpdateDTO updateDTO){
        Optional<User> user = userRepository.findById(username);
        if(user.isEmpty()){
            return false;
        }
        else{
            User updatedUser = user.get();
            updatedUser.setFirstName(updateDTO.getFirstName());
            updatedUser.setMiddleName(updateDTO.getMiddleName());
            updatedUser.setLastName(updateDTO.getLastName());
            updatedUser.setBirthDate(updateDTO.getBirthDate());
            updatedUser.setGender(updateDTO.getGender());
            updatedUser.setNationality(updateDTO.getNationality());
            userRepository.save(updatedUser);
            return true;
        }

    }

    //TODO: Add Reservations history
    //TODO: Add Upcoming reservations

}
