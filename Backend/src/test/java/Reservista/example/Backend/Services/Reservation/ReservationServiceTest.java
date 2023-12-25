package Reservista.example.Backend.Services.Reservation;

import Reservista.example.Backend.DAOs.*;
import Reservista.example.Backend.DTOs.Reservation.ReservationDTO;
import Reservista.example.Backend.DTOs.Reservation.ReservedRoomDTO;
import Reservista.example.Backend.DTOs.Response.ReservationResponseDTO;
import Reservista.example.Backend.DTOs.Response.ResponseDTO;
import Reservista.example.Backend.Enums.StatusCode;
import Reservista.example.Backend.Models.EntityClasses.User;
import Reservista.example.Backend.Models.EntityClasses.Voucher;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.Duration;
import java.time.Instant;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class ReservationServiceTest {
    @Autowired
    ReservationService reservationService;
    @Autowired
    ReservationRepository reservationRepository;

    @Autowired
    VoucherHandler voucherHandler;


        @Test
        void reserveAvailableRooms(){
            List<ReservedRoomDTO> reservedRoomDTOS=new ArrayList<>();
            for (int i =0 ;i<3;i++) {
                ReservedRoomDTO reservedRoom = ReservedRoomDTO.builder()
                        .hasBreakfast(false)
                        .hasLunch(true)
                        .hasDinner(false)
                        .build();
                reservedRoomDTOS.add(reservedRoom);

            }
            ReservationDTO reservationDTO= ReservationDTO.builder()
                    .checkIn(Instant.parse("2024-12-14T12:00:00Z"))
                    .checkOut(Instant.parse("2023-12-16T12:00:00Z"))
                    .hotelID(UUID.fromString("001cc902-bea3-4381-86af-4064e3b90fc8"))
                    .roomDescriptionId(UUID.fromString("0002d331-89d7-4f19-b6bf-8cf553b767c5"))
                    .refundable(true)
                    .reservedRooms(reservedRoomDTOS)
                    .build();
            ResponseDTO<ReservationResponseDTO> responseDTO=reservationService.reserve("mariam",reservationDTO);
            assertEquals(responseDTO.getStatus(), StatusCode.STRIPE_PAYMENT_INTENT_SUCCESSFUL.getCode());
            reservationRepository.deleteById(responseDTO.getData().getReservationId());
    }
    @Test
    void reserveNotAvailableRoomsBecauseRoomExtendsRoomsOfThisTypeInHotel (){
        List<ReservedRoomDTO> reservedRoomDTOS=new ArrayList<>();
        for (int i =0 ;i<20;i++) {
            ReservedRoomDTO reservedRoom = ReservedRoomDTO.builder()
                    .hasBreakfast(false)
                    .hasLunch(true)
                    .hasDinner(false)
                    .build();
            reservedRoomDTOS.add(reservedRoom);

        }
        ReservationDTO reservationDTO= ReservationDTO.builder()
                .checkIn(Instant.parse("2024-12-14T12:00:00Z"))
                .checkOut(Instant.parse("2023-12-16T12:00:00Z"))
                .hotelID(UUID.fromString("001cc902-bea3-4381-86af-4064e3b90fc8"))
                .roomDescriptionId(UUID.fromString("0002d331-89d7-4f19-b6bf-8cf553b767c5"))
                .refundable(true)
                .reservedRooms(reservedRoomDTOS)
                .build();
        ResponseDTO<ReservationResponseDTO> responseDTO=reservationService.reserve("mariam",reservationDTO);
        assertEquals(responseDTO.getStatus(), StatusCode.NOT_AVAILABLE.getCode());
    }
    @Test
    void reserveWithNotAvailableRoomsBecauseOfInterleavedReservations(){
        List<ReservedRoomDTO> reservedRoomDTOS=new ArrayList<>();
        for (int i =0 ;i<3;i++) {
            ReservedRoomDTO reservedRoom = ReservedRoomDTO.builder()
                    .hasBreakfast(false)
                    .hasLunch(true)
                    .hasDinner(false)
                    .build();
            reservedRoomDTOS.add(reservedRoom);

        }
        ReservationDTO reservationDTO= ReservationDTO.builder()
                .checkIn(Instant.parse("2023-12-14T12:00:00Z"))
                .checkOut(Instant.parse("2024-01-12T12:00:00Z"))
                .hotelID(UUID.fromString("001cc902-bea3-4381-86af-4064e3b90fc8"))
                .roomDescriptionId(UUID.fromString("0002d331-89d7-4f19-b6bf-8cf553b767c5"))
                .refundable(true)
                .reservedRooms(reservedRoomDTOS)
                .build();
        ResponseDTO<ReservationResponseDTO> responseDTO=reservationService.reserve("mariam",reservationDTO);
        assertEquals(responseDTO.getStatus(), StatusCode.NOT_AVAILABLE.getCode());

    }
    @Test
    void reserveWithWrongVoucher(){
        List<ReservedRoomDTO> reservedRoomDTOS=new ArrayList<>();
        for (int i =0 ;i<3;i++) {
            ReservedRoomDTO reservedRoom = ReservedRoomDTO.builder()
                    .hasBreakfast(false)
                    .hasLunch(true)
                    .hasDinner(false)
                    .build();
            reservedRoomDTOS.add(reservedRoom);

        }
        ReservationDTO reservationDTO= ReservationDTO.builder()
                .checkIn(Instant.parse("2023-12-14T12:00:00Z"))
                .checkOut(Instant.parse("2024-01-12T12:00:00Z"))
                .hotelID(UUID.fromString("001cc902-bea3-4381-86af-4064e3b90fc8"))
                .roomDescriptionId(UUID.fromString("0002d331-89d7-4f19-b6bf-8cf553b767c5"))
                .refundable(true)
                .voucherCode("hi")
                .reservedRooms(reservedRoomDTOS)
                .build();
        ResponseDTO<ReservationResponseDTO> responseDTO=reservationService.reserve("mariam",reservationDTO);
        assertEquals(responseDTO.getStatus(), StatusCode.NOT_FOUND.getCode());

    }


    @Test
    public void testSuccess(){
        User user = User.builder()
                .userName("marwan123")
                .email("keko@gmail.com")
                .vouchers(new HashSet<>())
                .build();

        Voucher voucher = Voucher.builder()
                .voucherCode("WELCOME50")
                .discountRate(50)
                .expiresAt(Instant.now().plus(Duration.ofDays(4))).build();

        StatusCode statusCode = voucherHandler.handleVoucher(user, voucher);

        assertEquals(StatusCode.SUCCESS, statusCode);
    }

    @Test
    public void testNotFOUND(){
        User user = User.builder()
                .userName("marwan123")
                .email("keko@gmail.com")
                .vouchers(new HashSet<>())
                .build();
        StatusCode statusCode = voucherHandler.handleVoucher(user, null);

        assertEquals(StatusCode.NOT_FOUND, statusCode);
    }

    @Test
    public void testExpiredCode(){
        User user = User.builder()
                .userName("marwan123")
                .email("keko@gmail.com")
                .vouchers(new HashSet<>())
                .build();

        Voucher voucher = Voucher.builder()
                .voucherCode("WELCOME50")
                .discountRate(50)
                .expiresAt(Instant.now().minus(Duration.ofDays(4))).build();

        StatusCode statusCode = voucherHandler.handleVoucher(user, voucher);

        assertEquals(StatusCode.EXPIREDCODE, statusCode);
    }

    @Test
    public void testUsedVoucher(){
        User user = User.builder()
                .userName("marwan123")
                .email("keko@gmail.com")
                .vouchers(new HashSet<>())
                .build();

        Voucher voucher = Voucher.builder()
                .voucherCode("WELCOME50")
                .discountRate(50)
                .expiresAt(Instant.now().plus(Duration.ofDays(4))).build();

        user.getVouchers().add(voucher);

        StatusCode statusCode = voucherHandler.handleVoucher(user, voucher);

        assertEquals(StatusCode.USEDVOUCHER, statusCode);
    }
    @Test
    void reserveWithValidVoucher(){
        List<ReservedRoomDTO> reservedRoomDTOS=new ArrayList<>();
        for (int i =0 ;i<3;i++) {
            ReservedRoomDTO reservedRoom = ReservedRoomDTO.builder()
                    .hasBreakfast(false)
                    .hasLunch(true)
                    .hasDinner(false)
                    .build();
            reservedRoomDTOS.add(reservedRoom);

        }
        ReservationDTO reservationDTO= ReservationDTO.builder()
                .checkIn(Instant.parse("2023-02-14T12:00:00Z"))
                .checkOut(Instant.parse("2024-03-12T12:00:00Z"))
                .hotelID(UUID.fromString("001cc902-bea3-4381-86af-4064e3b90fc8"))
                .roomDescriptionId(UUID.fromString("0002d331-89d7-4f19-b6bf-8cf553b767c5"))
                .refundable(true)
                .voucherCode("hello")
                .reservedRooms(reservedRoomDTOS)
                .build();
        ResponseDTO<ReservationResponseDTO> responseDTO=reservationService.reserve("samni",reservationDTO);
        assertEquals(responseDTO.getStatus(), StatusCode.USEDVOUCHER.getCode());


    }


}