package Lost.Found.Tracker.Repository;

import Lost.Found.Tracker.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}