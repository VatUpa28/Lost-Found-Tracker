package Lost.Found.Tracker.Repository;

import Lost.Found.Tracker.Entity.Item;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemRepository extends JpaRepository<Item, Long> {
}