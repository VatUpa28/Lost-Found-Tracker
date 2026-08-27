package lft.service;

import lft.entity.Report;
import lft.entity.User;
import lft.entity.Item;
import lft.repository.ReportRepository;
import lft.repository.UserRepository;
import lft.repository.ItemRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ReportService {

    private final ReportRepository reportRepository;
    private final UserRepository userRepository;
    private final ItemRepository itemRepository;

    public ReportService(
            ReportRepository reportRepository,
            UserRepository userRepository,
            ItemRepository itemRepository) {

        this.reportRepository = reportRepository;
        this.userRepository = userRepository;
        this.itemRepository = itemRepository;
    }

    public List<Report> getAllReports() {
        return reportRepository.findAll();
    }

    public Optional<Report> getReportById(Long id) {
        return reportRepository.findById(id);
    }

    public Report createReport(Report report) {

        if (report.getBy() == null || report.getBy().getId() == null) {
            throw new RuntimeException("Report creator (by) is required");
        }

        if (report.getItem() == null || report.getItem().getId() == null) {
            throw new RuntimeException("Item is required");
        }

        Long userId = report.getBy().getId();
        Long itemId = report.getItem().getId();

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException(
                        "User not found: " + userId));

        Item item = itemRepository.findById(itemId)
                .orElseThrow(() -> new RuntimeException(
                        "Item not found: " + itemId));

        report.setBy(user);
        report.setItem(item);

        return reportRepository.save(report);
    }

    public Report updateReport(Report report) {
        return reportRepository.save(report);
    }

    public void deleteReport(Long id) {
        reportRepository.deleteById(id);
    }
}