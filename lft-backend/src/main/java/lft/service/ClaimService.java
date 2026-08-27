package lft.service;

import lft.entity.Claim;
import lft.entity.User;
import lft.entity.Item;
import lft.repository.ClaimRepository;
import lft.repository.UserRepository;
import lft.repository.ItemRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClaimService {

    private final ClaimRepository claimRepository;
    private final UserRepository userRepository;
    private final ItemRepository itemRepository;

    public ClaimService(
            ClaimRepository claimRepository,
            UserRepository userRepository,
            ItemRepository itemRepository) {

        this.claimRepository = claimRepository;
        this.userRepository = userRepository;
        this.itemRepository = itemRepository;
    }

    public List<Claim> getAllClaims() {
        return claimRepository.findAll();
    }

    public Optional<Claim> getClaimById(Long id) {
        return claimRepository.findById(id);
    }

    public Claim createClaim(Claim claim) {

        if (claim.getBy() == null || claim.getBy().getId() == null) {
            throw new RuntimeException("Claim creator (by) is required");
        }

        if (claim.getItem() == null || claim.getItem().getId() == null) {
            throw new RuntimeException("Item is required");
        }

        Long userId = claim.getBy().getId();
        Long itemId = claim.getItem().getId();

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException(
                        "User not found: " + userId));

        Item item = itemRepository.findById(itemId)
                .orElseThrow(() -> new RuntimeException(
                        "Item not found: " + itemId));

        claim.setBy(user);
        claim.setItem(item);

        return claimRepository.save(claim);
    }

    public Claim updateClaim(Claim claim) {
        return claimRepository.save(claim);
    }

    public void deleteClaim(Long id) {
        claimRepository.deleteById(id);
    }
}