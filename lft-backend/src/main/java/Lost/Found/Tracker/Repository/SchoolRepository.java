package Lost.Found.Tracker.Repository;

import Lost.Found.Tracker.Entity.School;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SchoolRepository extends JpaRepository<School, Long> {
}