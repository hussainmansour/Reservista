package Reservista.example.Backend.Controllers;

import Reservista.example.Backend.DAOs.HotelImageRepository;
import Reservista.example.Backend.Models.EntityClasses.HotelImage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.awt.*;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/api/images")
public class ImageController {

    @Autowired
    private HotelImageRepository hotelImageRepository;

    @GetMapping("/hotel")
    public ResponseEntity<byte[]> getImage(@RequestParam("id") UUID imageId) {
        Optional<HotelImage> optionalImage = hotelImageRepository.findById(imageId);

        if (optionalImage.isPresent()) {
            HotelImage image = optionalImage.get();

             byte[] imageData = image.getSource();

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.IMAGE_JPEG);

            return new ResponseEntity<>(imageData, headers, HttpStatus.OK);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}

