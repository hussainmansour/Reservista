package Reservista.example.Backend.MailComponent.mailParsers;

import Reservista.example.Backend.DTOs.Admin.VoucherDTO;
import Reservista.example.Backend.MailComponent.Mail;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

public class VoucherMailParser extends Mail {
    public VoucherMailParser(VoucherDTO voucherDTO){
        LocalDateTime dateTime = LocalDateTime.ofInstant(voucherDTO.getExpiresAt(), ZoneId.systemDefault());

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String formattedDate = dateTime.format(formatter);
        setSubject("NEW VOUCHER!");
        setBody("HEY !" +
                "Reservista just released an exclusive new voucher,"+
                " and it's available for a limited time only!, will expire at "+formattedDate
                +"get "+ voucherDTO.getDiscountRate()+" % off when using this voucher ('"+voucherDTO.getVoucherCode()+"')\n");

    }
}
