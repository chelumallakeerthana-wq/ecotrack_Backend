package com.ecotrack.config;

import com.ecotrack.model.CloudResource;
import com.ecotrack.repository.CloudResourceRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import java.time.LocalDateTime;

/**
 * DataInitializer seeds the database with sample cloud resources
 * when the application starts.
 */
@Component
public class DataInitializer implements CommandLineRunner {

    private final CloudResourceRepository repository;

    public DataInitializer(CloudResourceRepository repository) {
        this.repository = repository;
    }

    @Override
    public void run(String... args) {
        System.out.println("Seeding sample cloud resources...");

        // Helper method creates and saves each resource
        save("Production Web Server",  "AWS",   "VM",         "us-east-1",     250.00, 85.5,  "Active", 90);
        save("User Data S3 Bucket",    "AWS",   "Storage",    "us-east-1",      45.00, 12.3,  "Active", 60);
        save("CI/CD Pipeline Runner",  "AWS",   "Container",  "us-west-2",     120.00, 38.7,  "Active", 45);
        save("Staging API Server",     "Azure", "VM",         "eastus",        180.00, 55.2,  "Active", 30);
        save("Azure SQL Database",     "Azure", "Database",   "westeurope",    320.00, 98.4,  "Active", 120);
        save("Dev Test Environment",   "Azure", "VM",         "eastus",         85.00, 28.1,  "Idle",   15);
        save("Analytics Database",     "GCP",   "Database",   "us-central1",   180.00, 42.6,  "Active", 75);
        save("ML Training Cluster",    "GCP",   "VM",         "us-central1",   480.00, 145.0, "Active", 20);
        save("Cloud Functions API",    "GCP",   "Serverless", "europe-west1",   35.00,  8.2,  "Active", 50);
        save("Legacy Load Balancer",   "AWS",   "Network",    "us-east-1",      65.00, 18.9,  "Idle",   200);

        System.out.println("Seeded " + repository.count() + " sample resources.");
    }

    /** Helper to create and save a CloudResource */
    private void save(String name, String provider, String type, String region,
                      double cost, double carbon, String status, int daysAgo) {
        CloudResource r = new CloudResource();
        r.setName(name);
        r.setProvider(provider);
        r.setResourceType(type);
        r.setRegion(region);
        r.setMonthlyCostUsd(cost);
        r.setCarbonFootprintKg(carbon);
        r.setStatus(status);
        r.setCreatedAt(LocalDateTime.now().minusDays(daysAgo));
        repository.save(r);
    }
}
