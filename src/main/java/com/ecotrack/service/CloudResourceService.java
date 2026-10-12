package com.ecotrack.service;

import com.ecotrack.dto.DashboardSummary;
import com.ecotrack.model.CloudResource;
import com.ecotrack.repository.CloudResourceRepository;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Service layer for CloudResource business logic.
 *
 * This class sits between the Controller (HTTP layer) and the
 * Repository (database layer). It contains all the business logic
 * such as computing dashboard summaries.
 */
@Service
public class CloudResourceService {

    private final CloudResourceRepository repository;

    public CloudResourceService(CloudResourceRepository repository) {
        this.repository = repository;
    }

    // ==================== CRUD Operations ====================

    public List<CloudResource> getAllResources() {
        return repository.findAll();
    }

    public CloudResource getResourceById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Resource not found with id: " + id));
    }

    public CloudResource createResource(CloudResource resource) {
        return repository.save(resource);
    }

    public CloudResource updateResource(Long id, CloudResource updatedResource) {
        CloudResource existing = getResourceById(id);
        existing.setName(updatedResource.getName());
        existing.setProvider(updatedResource.getProvider());
        existing.setResourceType(updatedResource.getResourceType());
        existing.setRegion(updatedResource.getRegion());
        existing.setMonthlyCostUsd(updatedResource.getMonthlyCostUsd());
        existing.setCarbonFootprintKg(updatedResource.getCarbonFootprintKg());
        existing.setStatus(updatedResource.getStatus());
        return repository.save(existing);
    }

    public void deleteResource(Long id) {
        if (!repository.existsById(id)) {
            throw new RuntimeException("Resource not found with id: " + id);
        }
        repository.deleteById(id);
    }

    // ==================== Filter Operations ====================

    public List<CloudResource> getByProvider(String provider) {
        return repository.findByProvider(provider);
    }

    public List<CloudResource> getByStatus(String status) {
        return repository.findByStatus(status);
    }

    // ==================== Dashboard Summary ====================

    public DashboardSummary getDashboardSummary() {
        List<CloudResource> allResources = repository.findAll();

        long totalResources = allResources.size();

        double totalCost = allResources.stream()
                .mapToDouble(r -> r.getMonthlyCostUsd() != null ? r.getMonthlyCostUsd() : 0)
                .sum();

        double totalCarbon = allResources.stream()
                .mapToDouble(r -> r.getCarbonFootprintKg() != null ? r.getCarbonFootprintKg() : 0)
                .sum();

        long activeResources = allResources.stream()
                .filter(r -> "Active".equalsIgnoreCase(r.getStatus()))
                .count();

        Map<String, Double> costByProvider = allResources.stream()
                .collect(Collectors.groupingBy(
                        CloudResource::getProvider,
                        Collectors.summingDouble(r -> r.getMonthlyCostUsd() != null ? r.getMonthlyCostUsd() : 0)
                ));

        Map<String, Double> carbonByProvider = allResources.stream()
                .collect(Collectors.groupingBy(
                        CloudResource::getProvider,
                        Collectors.summingDouble(r -> r.getCarbonFootprintKg() != null ? r.getCarbonFootprintKg() : 0)
                ));

        Map<String, Double> costByResourceType = allResources.stream()
                .collect(Collectors.groupingBy(
                        CloudResource::getResourceType,
                        Collectors.summingDouble(r -> r.getMonthlyCostUsd() != null ? r.getMonthlyCostUsd() : 0)
                ));

        Map<String, Long> resourcesByStatus = allResources.stream()
                .collect(Collectors.groupingBy(
                        CloudResource::getStatus,
                        Collectors.counting()
                ));

        DashboardSummary summary = new DashboardSummary();
        summary.setTotalResources(totalResources);
        summary.setTotalMonthlyCost(Math.round(totalCost * 100.0) / 100.0);
        summary.setTotalCarbonFootprint(Math.round(totalCarbon * 100.0) / 100.0);
        summary.setActiveResources(activeResources);
        summary.setCostByProvider(costByProvider);
        summary.setCarbonByProvider(carbonByProvider);
        summary.setCostByResourceType(costByResourceType);
        summary.setResourcesByStatus(resourcesByStatus);
        return summary;
    }
}
