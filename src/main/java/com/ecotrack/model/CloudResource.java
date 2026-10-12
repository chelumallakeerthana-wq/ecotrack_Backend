package com.ecotrack.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.PositiveOrZero;
import java.time.LocalDateTime;

/**
 * CloudResource represents a cloud infrastructure resource
 * (e.g., a virtual machine, database, or storage bucket).
 *
 * Each resource tracks its monthly cost in USD and estimated
 * carbon footprint in kilograms of CO2.
 */
@Entity
@Table(name = "cloud_resources")
public class CloudResource {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /** Human-readable name, e.g. "Production Web Server" */
    @NotBlank(message = "Resource name is required")
    private String name;

    /** Cloud provider: AWS, Azure, GCP, or Other */
    @NotBlank(message = "Provider is required")
    private String provider;

    /** Type of resource: VM, Storage, Database, Network, Container, Serverless */
    @NotBlank(message = "Resource type is required")
    private String resourceType;

    /** Cloud region, e.g. "us-east-1" or "europe-west1" */
    private String region;

    /** Monthly cost in US dollars */
    @PositiveOrZero(message = "Cost must be zero or positive")
    private Double monthlyCostUsd;

    /** Estimated monthly carbon footprint in kg of CO2 */
    @PositiveOrZero(message = "Carbon footprint must be zero or positive")
    private Double carbonFootprintKg;

    /** Current status: Active, Idle, or Terminated */
    private String status = "Active";

    /** Timestamp when this resource was first tracked */
    private LocalDateTime createdAt = LocalDateTime.now();

    // ==================== Constructors ====================

    public CloudResource() {
    }

    public CloudResource(String name, String provider, String resourceType, String region,
                         Double monthlyCostUsd, Double carbonFootprintKg, String status,
                         LocalDateTime createdAt) {
        this.name = name;
        this.provider = provider;
        this.resourceType = resourceType;
        this.region = region;
        this.monthlyCostUsd = monthlyCostUsd;
        this.carbonFootprintKg = carbonFootprintKg;
        this.status = status;
        this.createdAt = createdAt;
    }

    // ==================== Getters & Setters ====================

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getProvider() { return provider; }
    public void setProvider(String provider) { this.provider = provider; }

    public String getResourceType() { return resourceType; }
    public void setResourceType(String resourceType) { this.resourceType = resourceType; }

    public String getRegion() { return region; }
    public void setRegion(String region) { this.region = region; }

    public Double getMonthlyCostUsd() { return monthlyCostUsd; }
    public void setMonthlyCostUsd(Double monthlyCostUsd) { this.monthlyCostUsd = monthlyCostUsd; }

    public Double getCarbonFootprintKg() { return carbonFootprintKg; }
    public void setCarbonFootprintKg(Double carbonFootprintKg) { this.carbonFootprintKg = carbonFootprintKg; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }

    /**
     * Automatically set the creation timestamp before persisting.
     */
    @PrePersist
    protected void onCreate() {
        if (createdAt == null) {
            createdAt = LocalDateTime.now();
        }
    }
}
