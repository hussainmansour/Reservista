package Reservista.example.Backend.Services.Admin;

import Reservista.example.Backend.DAOs.AdminRepository;
import Reservista.example.Backend.DAOs.UserRepository;
import Reservista.example.Backend.DAOs.VoucherRepository;
import Reservista.example.Backend.DTOs.Admin.AdminDTO;
import Reservista.example.Backend.DTOs.Admin.VoucherDTO;
import Reservista.example.Backend.Enums.ErrorCode;
import Reservista.example.Backend.Error.GlobalException;
import Reservista.example.Backend.MailComponent.MailService;
import Reservista.example.Backend.MailComponent.mailParsers.VoucherMailParser;
import Reservista.example.Backend.Models.EntityClasses.Admin;
import Reservista.example.Backend.Models.EntityClasses.Voucher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.http.HttpStatus;

import java.util.List;

@Service
public class AdminService {

    @Autowired
    AdminRepository adminRepository;

    @Autowired
    VoucherRepository voucherRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private MailService mailService;
    @Autowired
    private UserRepository userRepository;

    public void addVoucher(VoucherDTO voucherDTO) throws GlobalException {
        if(voucherRepository.existsByVoucherCode(voucherDTO.getVoucherCode())){
            throw new GlobalException(ErrorCode.VOUCHER_ALREADY_EXISTS, HttpStatus.CONFLICT);
        }
        sendVoucherToUsers(voucherDTO);
        Voucher voucher = Voucher.builder()
                .voucherCode(voucherDTO.getVoucherCode())
                .discountRate(voucherDTO.getDiscountRate())
                .expiresAt(voucherDTO.getExpiresAt()).build();

        voucherRepository.save(voucher);
    }
    public void sendVoucherToUsers(VoucherDTO voucherDTO){
        VoucherMailParser mail=new VoucherMailParser(voucherDTO);
        List<String> emails=userRepository.findAllUsersEmails();
        mailService.sendMailToAll(mail,emails);
    }

    public void addAdmin(AdminDTO adminDTO) throws GlobalException {

        if(adminRepository.existsByAdminName(adminDTO.getAdminName())){
            throw new GlobalException(ErrorCode.ADMIN_ALREADY_EXISTS, HttpStatus.CONFLICT);
        }

        Admin admin = Admin.builder()
                .adminName(adminDTO.getAdminName())
                .password(passwordEncoder.encode(adminDTO.getPassword())).build();

        adminRepository.save(admin);
    }
}
