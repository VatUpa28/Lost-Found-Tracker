package Lost.Found.Tracker.Repository;

import Lost.Found.Tracker.Entity.Report;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReportRepository extends JpaRepository<Report, Long> {
}