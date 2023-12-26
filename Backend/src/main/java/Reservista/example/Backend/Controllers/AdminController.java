package Reservista.example.Backend.Controllers;

import Reservista.example.Backend.DTOs.Admin.AdminDTO;
import Reservista.example.Backend.DTOs.Admin.VoucherDTO;
import Reservista.example.Backend.Error.GlobalException;
import Reservista.example.Backend.Services.Admin.AdminService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test/admin")
public class AdminController {
    @Autowired
    AdminService adminService;

    @PostMapping("/voucher")
    ResponseEntity<String> addVoucher(@Valid @RequestBody VoucherDTO voucherDTO) throws GlobalException {
        adminService.addVoucher(voucherDTO);
        return ResponseEntity.ok("Voucher added successfully");
    }

    @PostMapping("/newAdmin")
    ResponseEntity<String> addAdmin(@Valid @RequestBody AdminDTO adminDTO) throws GlobalException {
        adminService.addAdmin(adminDTO);
        return ResponseEntity.ok("Admin added successfully");
    }
}
