package lft.service;

import lft.entity.Item;
import lft.entity.User;
import lft.entity.School;
import lft.repository.ItemRepository;
import lft.repository.UserRepository;
import lft.repository.SchoolRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ItemService {

    private final ItemRepository itemRepository;
    private final UserRepository userRepository;
    private final SchoolRepository schoolRepository;

    public ItemService(
            ItemRepository itemRepository,
            UserRepository userRepository,
            SchoolRepository schoolRepository) {

        this.itemRepository = itemRepository;
        this.userRepository = userRepository;
        this.schoolRepository = schoolRepository;
    }

    public List<Item> getAllItems() {
        return itemRepository.findAll();
    }

    public Optional<Item> getItemById(Long id) {
        return itemRepository.findById(id);
    }

    public Item createItem(Item item) {

        Long ownerId = item.getOwner().getId();
        Long schoolId = item.getSchool().getId();

        User owner = userRepository.findById(ownerId)
                .orElseThrow(() -> new RuntimeException("Owner not found"));

        School school = schoolRepository.findById(schoolId)
                .orElseThrow(() -> new RuntimeException("School not found"));

        item.setOwner(owner);
        item.setSchool(school);

        return itemRepository.save(item);
    }

    public Item updateItem(Item item) {
        return itemRepository.save(item);
    }

    public void deleteItem(Long id) {
        itemRepository.deleteById(id);
    }
}