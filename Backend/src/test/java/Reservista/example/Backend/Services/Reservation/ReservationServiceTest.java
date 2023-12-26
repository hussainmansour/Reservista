//package Reservista.example.Backend.Services.Reservation;
//
//import Reservista.example.Backend.DAOs.HotelRepository;
//import Reservista.example.Backend.DAOs.ReservationRepository;
//import Reservista.example.Backend.DAOs.RoomDescriptionRepository;
//import Reservista.example.Backend.DAOs.UserRepository;
//import Reservista.example.Backend.DTOs.Reservation.ReservationDTO;
//import Reservista.example.Backend.DTOs.Reservation.ReservedRoomDTO;
//import Reservista.example.Backend.Models.EmbeddedClasses.RoomFoodOptions;
//import Reservista.example.Backend.Models.EntityClasses.Reservation;
//import Reservista.example.Backend.Models.EntityClasses.ReservedRoom;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//
//import java.time.Duration;
//import java.time.Instant;
//import java.util.*;
//
//import static org.hibernate.boot.model.process.spi.MetadataBuildingProcess.build;
//import static org.junit.jupiter.api.Assertions.*;
//@SpringBootTest
//class ReservationServiceTest {
//
//    @Autowired
//    RoomDescriptionRepository roomDescriptionRepository;
//
//    @Autowired
//    UserRepository userRepository;
//
//    @Autowired
//    HotelRepository hotelRepository;
//
//    @Autowired
//    ReservationRepository reservationRepository;
//
//
//    @Test
//    void test(){
//        System.out.println(roomDescriptionRepository.findNumberOfRoomsByRoomDescriptionId(UUID.fromString("0002d331-89d7-4f19-b6bf-8cf553b767c5")));
//    }
//
//    @Test
//    public void prepareReservation() {
//        ReservedRoomDTO reservedRoomDTO = new ReservedRoomDTO(true,false,true,50);
//        List<ReservedRoomDTO> reservedRooms=new ArrayList<>();
//        reservedRooms.add(reservedRoomDTO);
//        reservedRooms.add(reservedRoomDTO);
//        reservedRooms.add(reservedRoomDTO);
//        ReservationDTO reservationDTO = ReservationDTO.builder()
//                        .hotelID(UUID.fromString("001cc902-bea3-4381-86af-4064e3b90fc8"))
//                        .checkIn(Instant.now())
//                        .checkOut(Instant.now().plus(Duration.ofDays(4)))
//                        .refundable(false)
//                        .voucherCode(null)
//                        .roomDescriptionId(UUID.fromString("0002d331-89d7-4f19-b6bf-8cf553b767c5"))
//                        .reservedRooms(reservedRooms)
//                        .build();
//
//        Set<ReservedRoom> reservedRoomSet = new HashSet<ReservedRoom>();
//        for (ReservedRoomDTO r : reservationDTO.getReservedRooms()) {
//            ReservedRoom reservedRoom = ReservedRoom.builder()
//                    .roomDescription(roomDescriptionRepository.findRoomDescriptionById(reservationDTO.getRoomDescriptionId()).orElseThrow())
//                    .roomFoodOptions(new RoomFoodOptions(r.isHasBreakfast(), r.isHasLunch(), r.isHasDinner()))
//                    .build();
//            reservedRoomSet.add(reservedRoom);
//
//        }
//
//        Reservation res =  Reservation.builder()
//                    .user(userRepository.findByUserName(reservationDTO.getUserName()).orElseThrow())
//                    .hotel(hotelRepository.findById(reservationDTO.getHotelID()).orElseThrow())
//                    .checkIn(reservationDTO.getCheckIn())
//                    .checkOut(reservationDTO.getCheckOut())
//                    .reservedRooms(reservedRoomSet)
//                    .isRefundable(reservationDTO.isRefundable())
//                    .isConfirmed(false)
//                    .build();
//
//        reservationRepository.save(res);
//    }
//
//
//}