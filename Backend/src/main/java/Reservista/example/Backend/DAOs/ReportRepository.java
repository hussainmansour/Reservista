package Reservista.example.Backend.DAOs;


import Reservista.example.Backend.Models.EntityClasses.Report;
import Reservista.example.Backend.Models.IDClasses.ReportId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReportRepository extends JpaRepository<Report, ReportId> {
}
