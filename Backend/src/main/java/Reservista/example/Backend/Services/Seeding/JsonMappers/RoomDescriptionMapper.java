package Reservista.example.Backend.Services.Seeding.JsonMappers;

import Reservista.example.Backend.Config.ImageUtil;
import Reservista.example.Backend.Models.EmbeddedClasses.RoomImage;
import Reservista.example.Backend.Models.EntityClasses.Room;
import Reservista.example.Backend.Models.EntityClasses.RoomDescription;
import Reservista.example.Backend.Services.Seeding.JsonDTOs.RoomDescriptionJsonDTO;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.Random;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class RoomDescriptionMapper {

    public RoomDescription mapToRoomDescription(RoomDescriptionJsonDTO roomDescriptionJsonDTO) {
        return RoomDescription.builder()
                .price(getPriceFromString(roomDescriptionJsonDTO.getTotalPrice()))
                .title(roomDescriptionJsonDTO.getHeaderTitle())
//                .roomImages(mapToRoomImages(roomDescriptionJsonDTO.getImageSources()))
                .roomDetails(roomDescriptionJsonDTO.getRoomDetails())
                .capacity(getCapacityFromSleeps(roomDescriptionJsonDTO.getRoomDetails()))
                .build();
    }

    public Set<RoomImage> mapToRoomImages(Set<String> imageSources) {
        return imageSources.stream()
                .map(this::mapToRoomImage)
                .collect(Collectors.toSet());
    }

    public RoomImage mapToRoomImage(String imageSource) {
        return RoomImage.builder()
                .source(urlToByteArray(imageSource))
                .build();
    }

    public double getPriceFromString(String price) {
        return Arrays.stream(price.split(" "))
                .findFirst()
                .map(val -> val.substring(1))
                .map(Double::parseDouble)
                .orElse(getRandomPrice(0, 350));
    }

    public double getRandomPrice(double from, double to) {
        return from + (to - from) * new Random().nextDouble();
    }

    public byte[] urlToByteArray(String source) {
        return ImageUtil.convertImageUrlToBytes(source);
    }

    public int getCapacityFromSleeps(Set<String> roomDetails) {
        return roomDetails.stream()
                .filter(detail -> detail.contains("Sleeps"))
                .findFirst()
                .map(sleeps -> sleeps.split(" ")[1])
                .map(Integer::parseInt)
                .orElse(0);
    }
}