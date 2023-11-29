package Reservista.example.Backend.DAOs;


import Reservista.example.Backend.Models.Report;
import Reservista.example.Backend.Models.ReportId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReportRepository extends JpaRepository<Report, ReportId> {
}
