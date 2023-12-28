package Reservista.example.Backend.DAOs;

import Reservista.example.Backend.Models.EntityClasses.Voucher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface VoucherRepository extends JpaRepository<Voucher,String> {
    Optional<Voucher> findByVoucherCode(String voucherCode);
    boolean existsByVoucherCode(String voucherCode);
}
