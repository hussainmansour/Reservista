package Reservista.example.Backend.Services.Admin;

import Reservista.example.Backend.DAOs.AdminRepository;
import Reservista.example.Backend.DAOs.VoucherRepository;
import Reservista.example.Backend.DTOs.Admin.AdminDTO;
import Reservista.example.Backend.DTOs.Admin.VoucherDTO;
import Reservista.example.Backend.Enums.StatusCode;
import Reservista.example.Backend.Error.GlobalException;
import Reservista.example.Backend.Error.GlobalExceptionHandler;
import Reservista.example.Backend.Models.EntityClasses.Admin;
import Reservista.example.Backend.Models.EntityClasses.Voucher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.http.HttpStatus;

@Service
public class AdminService {

    @Autowired
    AdminRepository adminRepository;

    @Autowired
    VoucherRepository voucherRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public void addVoucher(VoucherDTO voucherDTO) throws GlobalException {
        if(voucherRepository.existsByVoucherCode(voucherDTO.getVoucherCode())){
            throw new GlobalException(StatusCode.VOUCHER_ALREADY_EXISTS, HttpStatus.NOT_ACCEPTABLE);
        }

        Voucher voucher = Voucher.builder()
                .voucherCode(voucherDTO.getVoucherCode())
                .discountRate(voucherDTO.getDiscountRate())
                .expiresAt(voucherDTO.getExpiresAt()).build();

        voucherRepository.save(voucher);
    }

    public void addAdmin(AdminDTO adminDTO) throws GlobalException {

        if(adminRepository.existsByAdminName(adminDTO.getAdminName())){
            throw new GlobalException(StatusCode.ADMIN_ALREADY_EXISTS, HttpStatus.NOT_ACCEPTABLE);
        }

        Admin admin = Admin.builder()
                .adminName(adminDTO.getAdminName())
                .password(passwordEncoder.encode(adminDTO.getPassword())).build();

        adminRepository.save(admin);
    }
}
