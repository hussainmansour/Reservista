package Reservista.example.Backend.Services.Reservation;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.time.Instant;
import java.time.LocalDateTime;
import java.util.*;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import Reservista.example.Backend.DAOs.*;
import Reservista.example.Backend.DTOs.Reservation.ReservationRequestDTO;
import Reservista.example.Backend.DTOs.Reservation.ReservationResponseDTO;
import Reservista.example.Backend.Error.GlobalException;
import Reservista.example.Backend.Models.EmbeddedClasses.RoomFoodOptions;
import Reservista.example.Backend.Models.EntityClasses.*;
import Reservista.example.Backend.Services.Reservation.RoomAvailabilityHandler;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.stubbing.OngoingStubbing;
import org.springframework.http.HttpStatus;

class RoomAvailabilityHandlerTest {

    @Mock
    private RoomDescriptionRepository roomDescriptionRepository;

    @Mock
    private UserRepository userRepository;

    @Mock
    private HotelRepository hotelRepository;

    @Mock
    private ReservedRoomRepository reservedRoomRepository;

    @Mock
    private ReservationRepository reservationRepository;

    @InjectMocks
    private RoomAvailabilityHandler roomAvailabilityHandler;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testHandleRequestWithAvailableRooms() throws Exception {
        // Mock dependencies
        when(roomDescriptionRepository.findNumberOfRoomsByRoomDescriptionId(ArgumentMatchers.any(UUID.class)))
                .thenReturn(5);
        when(reservationRepository.save(ArgumentMatchers.any())).thenReturn(new Reservation());

        // Create a ReservationRequestDTO for testing
        ReservationRequestDTO reservationRequestDTO = createTestReservationRequestDTO();

        assertThrows(NoSuchElementException.class, () -> {
            roomAvailabilityHandler.handleRequest(reservationRequestDTO);
        });

    }

    @Test
    void testHandleRequestWithNotEnoughAvailableRooms() {
        // Mock dependencies
        when(roomDescriptionRepository.findNumberOfRoomsByRoomDescriptionId(ArgumentMatchers.any(UUID.class)))
                .thenReturn(2);

        // Create a ReservationRequestDTO for testing
        ReservationRequestDTO reservationRequestDTO = createTestReservationRequestDTO();

        // Test the handleRequest method and expect an exception
        assertThrows(RuntimeException.class, () -> {
            roomAvailabilityHandler.handleRequest(reservationRequestDTO);
        });
    }

    @Test
    void testPrepareReservation() {
        // Mock dependencies
        when(roomDescriptionRepository.findRoomDescriptionById(ArgumentMatchers.any(UUID.class)))
                .thenReturn(Optional.of(new RoomDescription())); // Add appropriate setup for your test
        when(userRepository.findByUserName(ArgumentMatchers.anyString())).thenReturn(Optional.of(new User()));
        when(hotelRepository.findById(ArgumentMatchers.any(UUID.class))).thenReturn(Optional.of(new Hotel()));

        // Create a ReservationRequestDTO for testing
        ReservationRequestDTO reservationRequestDTO = createTestReservationRequestDTO();

        // Test the prepareReservation method
        Reservation reservation = roomAvailabilityHandler.prepareReservation(reservationRequestDTO);

        // Add assertions based on the expected behavior of your method
        assertNotNull(reservation);
        // Add more assertions as needed
    }

    @Test
    void testCheckAndReserveWithEnoughAvailableRooms() {
        // Mock dependencies
        when(reservedRoomRepository.getConflictedRoomNumbers(ArgumentMatchers.any(UUID.class), ArgumentMatchers.any(), ArgumentMatchers.any()))
                .thenReturn(new HashSet<>());
        when(reservationRepository.save(ArgumentMatchers.any())).thenReturn(new Reservation());

        // Test the checkAndReserve method
        //Don't create new reservation, Add all required fields to the reservation object
        Set<ReservedRoom> reservedRoomSet = new HashSet<ReservedRoom>();
        for (int i = 0; i < 5; i++) {
            ReservedRoom reservedRoom = ReservedRoom.builder()
                    .roomDescription(new RoomDescription())
                    .roomFoodOptions(new RoomFoodOptions(true, true, true))
                    .title("test")
                    .reservation(new Reservation())
                    .build();
            reservedRoomSet.add(reservedRoom);
        }
        RoomDescription roomDescription = new RoomDescription();
        roomDescription.setRoomCount(5);

        Reservation reservation = new Reservation();
        reservation.setCheckIn(Instant.now());
        reservation.setCheckOut(Instant.now().plusSeconds(86400));
        reservation.setReservedRooms(reservedRoomSet);
        reservation.setRoomDescription(roomDescription);




        Reservation reservationDone = roomAvailabilityHandler.checkAndReserve(reservation, UUID.randomUUID(), 5);

        // Add assertions based on the expected behavior of your method
        assertNotNull(reservationDone);
        // Add more assertions as needed
    }

    @Test
    void testDeleteAfter10min() {
        // Mock dependencies
        Reservation reservation = new Reservation();
        when(reservationRepository.findById(ArgumentMatchers.anyLong())).thenReturn(Optional.of(reservation));
        when(reservationRepository.save(ArgumentMatchers.any())).thenReturn(reservation);

        // Set the reservation creation time to a past time
        reservation.setReservationDate(Instant.now().minusSeconds(600));

        // Test the deleteAfter10min method
        roomAvailabilityHandler.deleteAfter10min(1L);

        // Verify that the reservation was deleted
        verify(reservationRepository).delete(reservation);
    }

    private ReservationRequestDTO createTestReservationRequestDTO() {
        ReservationRequestDTO reservationRequestDTO = new ReservationRequestDTO();
        reservationRequestDTO.setUserName("test");
        reservationRequestDTO.setHotelID(UUID.randomUUID());
        reservationRequestDTO.setRoomDescriptionId(UUID.randomUUID());
        reservationRequestDTO.setCheckIn(Instant.now());
        reservationRequestDTO.setCheckOut(Instant.now().plusSeconds(86400));
        reservationRequestDTO.setRefundable(true);
        reservationRequestDTO.setVoucherPercentage(0);
        reservationRequestDTO.setRoomTitle("test");
        reservationRequestDTO.setReservedRooms(new ArrayList<>());
        return reservationRequestDTO;
    }
}
