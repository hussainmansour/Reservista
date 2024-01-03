package Reservista.example.Backend.Services.Admin;

import static org.junit.jupiter.api.Assertions.*;
import Reservista.example.Backend.DAOs.AdminRepository;
import Reservista.example.Backend.DAOs.VoucherRepository;
import Reservista.example.Backend.DTOs.Admin.AdminDTO;
import Reservista.example.Backend.DTOs.Admin.VoucherDTO;
import Reservista.example.Backend.Enums.ErrorCode;
import Reservista.example.Backend.Error.GlobalException;
import Reservista.example.Backend.Models.EntityClasses.Admin;
import Reservista.example.Backend.Models.EntityClasses.Voucher;
import Reservista.example.Backend.Services.Admin.AdminService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.http.HttpStatus;

import java.time.Instant;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class AdminServiceTest {

    @Mock
    private AdminRepository adminRepository;

    @Mock
    private VoucherRepository voucherRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    @InjectMocks
    private AdminService adminService;

    public AdminServiceTest() {
        MockitoAnnotations.openMocks(this);
     }

    @Test
    void testAddVoucher() {
        VoucherDTO voucherDTO = new VoucherDTO("TESTVOUCHER", 10, Instant.now().plusSeconds(3600));

        when(voucherRepository.existsByVoucherCode(voucherDTO.getVoucherCode())).thenReturn(false);

        // Mock the return value of the save method
        Voucher savedVoucher = new Voucher(); // Create an instance of Voucher or use a mock
        when(voucherRepository.save(any(Voucher.class))).thenReturn(savedVoucher);

        assertDoesNotThrow(() -> adminService.addVoucher(voucherDTO));
    }


    @Test
    void testAddVoucherWhenVoucherExists() {
        VoucherDTO voucherDTO = new VoucherDTO("EXISTINGVOUCHER", 20, Instant.now().plusSeconds(3600));

        when(voucherRepository.existsByVoucherCode(voucherDTO.getVoucherCode())).thenReturn(true);

        GlobalException exception = assertThrows(GlobalException.class, () -> adminService.addVoucher(voucherDTO));
        assertEquals(ErrorCode.VOUCHER_ALREADY_EXISTS, exception.getErrorCode());
        assertEquals(HttpStatus.CONFLICT, exception.getHttpStatus());
    }

    @Test
    void testAddAdmin() {
        AdminDTO adminDTO = new AdminDTO("admin", "password");

        when(adminRepository.existsByAdminName(adminDTO.getAdminName())).thenReturn(false);
        // Mock the return value of the save method
        Admin savedAdmin = Admin.builder().adminName(adminDTO.getAdminName()).password(adminDTO.getPassword()).build();// Create an instance of Admin or use a mock
        when(adminRepository.save(savedAdmin)).thenReturn(savedAdmin);

        assertDoesNotThrow(() -> adminService.addAdmin(adminDTO));
    }



    @Test
    void testAddAdminWhenAdminExists() {
        AdminDTO adminDTO = new AdminDTO("existingAdmin", "password");

        when(adminRepository.existsByAdminName(adminDTO.getAdminName())).thenReturn(true);

        GlobalException exception = assertThrows(GlobalException.class, () -> adminService.addAdmin(adminDTO));
        assertEquals(ErrorCode.ADMIN_ALREADY_EXISTS, exception.getErrorCode());
        assertEquals(HttpStatus.CONFLICT, exception.getHttpStatus());
    }
}
