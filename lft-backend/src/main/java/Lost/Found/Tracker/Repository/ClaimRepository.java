package Lost.Found.Tracker.Repository;

import Lost.Found.Tracker.Entity.Claim;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClaimRepository extends JpaRepository<Claim, Long> {
}