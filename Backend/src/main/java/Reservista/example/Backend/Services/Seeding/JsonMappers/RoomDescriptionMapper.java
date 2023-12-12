package Reservista.example.Backend.Services.Seeding.JsonMappers;

import Reservista.example.Backend.Config.ImageUtil;
import Reservista.example.Backend.Models.EntityClasses.RoomImage;
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

        RoomDescription roomDescription = RoomDescription.builder()
                .price(getPriceFromString(roomDescriptionJsonDTO.getTotalPrice()))
                .title(roomDescriptionJsonDTO.getHeaderTitle())
                .roomImages(mapToRoomImages(roomDescriptionJsonDTO.getImageSources()))
                .roomDetails(roomDescriptionJsonDTO.getRoomDetails())
                .capacity(getCapacityFromSleeps(roomDescriptionJsonDTO.getRoomDetails()))
                .roomCount(getRandomInt(10,20))
                .build();
        roomDescription.getRoomImages().forEach(roomImage -> roomImage.setRoomDescription(roomDescription));
        return roomDescription;
    }

    public Set<RoomDescription> mapToRoomDescriptionSet(Set<RoomDescriptionJsonDTO> roomDescriptionJsonDTOs) {
        return roomDescriptionJsonDTOs.stream()
                .map(this::mapToRoomDescription)
                .collect(Collectors.toSet());
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

    public int getPriceFromString(String price) {
        return !price.isEmpty() ? Arrays.stream(price.split(" "))
                .findFirst()
                .map(val -> val.substring(1))
                .map(val -> val.replaceAll(",",""))
                .map(Integer::parseInt)
                .orElse(getRandomInt(150, 400)) : getRandomInt(150, 400);
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
                .orElse(1);
    }

    public int getRandomInt(int from, int to) {
        Random random = new Random();
        return from + random.nextInt(to - from + 1);
    }

}