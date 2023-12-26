//package Reservista.example.Backend.Services.Reservation;
//
//import Reservista.example.Backend.DAOs.RoomDescriptionRepository;
//import Reservista.example.Backend.DAOs.UserRepository;
//import Reservista.example.Backend.Models.EmbeddedClasses.FullName;
//import Reservista.example.Backend.Models.EntityClasses.Hotel;
//import Reservista.example.Backend.Models.EntityClasses.User;
//import Reservista.example.Backend.Services.Registration.OTPService;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
//import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
//import org.springframework.boot.test.context.SpringBootTest;
//
//import java.util.Optional;
//import java.util.UUID;
//
//import static org.junit.jupiter.api.Assertions.*;
//
//
//@DataJpaTest
//class RoomAvailabilityHandlerTest {
//    @Autowired
//    private TestEntityManager entityManager;
//
//    @Autowired
//    private RoomDescriptionRepository roomDescriptionRepository;
//
//    @Autowired
//    UserRepository userRepository;
//
//    @Autowired
//    private OTPService otpService;
//    @BeforeEach
//    void setUp() {
//        User user1 = User.builder()
//                .userName("mariam")
//                .fullName(
//                        FullName
//                                .builder()
//                                .firstName("mariam")
//                                .lastName("gerges")
//                                .build()
//                )
//                .email("mariam.gerges1188@gmail.com")
//                .password("jKK&123jgj")
//                .isActivated(true)
//                .build();
//        User user2 = User.builder()
//                .userName("mariamG")
//                .fullName(
//                        FullName
//                                .builder()
//                                .firstName("mariam")
//                                .lastName("gerges")
//                                .build()
//                )
//                .email("mariam.gerges575@gmail.com")
//                .password("jKK&123jgj")
//                .isActivated(true)
//                .build();
//        entityManager.persist(user1);
//        entityManager.persist(user2);
//        Hotel hotel1 = Hotel.builder().build();
//
//    }
//
//    @Test
//    void u(){
//       assertEquals(userRepository.existsByUserName("mariamgerges575@gmail.com"),true);
//    }
//
//
//}