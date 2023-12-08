package Reservista.example.Backend.Services.Profile;

import Reservista.example.Backend.DAOs.UserRepository;
import Reservista.example.Backend.DTOs.Profile.ProfileDTO;
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

    public Optional<ProfileDTO> updateProfile(ProfileDTO profileDTO){
        String username = profileDTO.getUserName();
        Optional<User> user = userRepository.findById(username);
        if(user.isEmpty()){
            return Optional.empty();
        }
        else{
            User updatedUser = user.get();
            updatedUser.setFirstName(profileDTO.getFirstName());
            updatedUser.setMiddleName(profileDTO.getMiddleName());
            updatedUser.setLastName(profileDTO.getLastName());
            updatedUser.setBirthDate(profileDTO.getBirthDate());
            updatedUser.setGender(profileDTO.getGender());
            updatedUser.setNationality(profileDTO.getNationality());
            userRepository.save(updatedUser);
            return Optional.of(profileDTO);
        }
    }

}
