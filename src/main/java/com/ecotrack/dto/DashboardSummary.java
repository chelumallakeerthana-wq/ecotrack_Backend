package com.ecotrack.dto;

import java.util.Map;

/**
 * DashboardSummary is a Data Transfer Object (DTO) that holds
 * aggregated statistics for the dashboard view.
 *
 * This is NOT a database entity — it's computed on the fly
 * from the CloudResource data.
 */
public class DashboardSummary {

    private long totalResources;
    private double totalMonthlyCost;
    private double totalCarbonFootprint;
    private long activeResources;
    private Map<String, Double> costByProvider;
    private Map<String, Double> carbonByProvider;
    private Map<String, Double> costByResourceType;
    private Map<String, Long> resourcesByStatus;

    public DashboardSummary() {
    }

    public DashboardSummary(long totalResources, double totalMonthlyCost,
                            double totalCarbonFootprint, long activeResources,
                            Map<String, Double> costByProvider,
                            Map<String, Double> carbonByProvider,
                            Map<String, Double> costByResourceType,
                            Map<String, Long> resourcesByStatus) {
        this.totalResources = totalResources;
        this.totalMonthlyCost = totalMonthlyCost;
        this.totalCarbonFootprint = totalCarbonFootprint;
        this.activeResources = activeResources;
        this.costByProvider = costByProvider;
        this.carbonByProvider = carbonByProvider;
        this.costByResourceType = costByResourceType;
        this.resourcesByStatus = resourcesByStatus;
    }

    // ==================== Getters & Setters ====================

    public long getTotalResources() { return totalResources; }
    public void setTotalResources(long totalResources) { this.totalResources = totalResources; }

    public double getTotalMonthlyCost() { return totalMonthlyCost; }
    public void setTotalMonthlyCost(double totalMonthlyCost) { this.totalMonthlyCost = totalMonthlyCost; }

    public double getTotalCarbonFootprint() { return totalCarbonFootprint; }
    public void setTotalCarbonFootprint(double totalCarbonFootprint) { this.totalCarbonFootprint = totalCarbonFootprint; }

    public long getActiveResources() { return activeResources; }
    public void setActiveResources(long activeResources) { this.activeResources = activeResources; }

    public Map<String, Double> getCostByProvider() { return costByProvider; }
    public void setCostByProvider(Map<String, Double> costByProvider) { this.costByProvider = costByProvider; }

    public Map<String, Double> getCarbonByProvider() { return carbonByProvider; }
    public void setCarbonByProvider(Map<String, Double> carbonByProvider) { this.carbonByProvider = carbonByProvider; }

    public Map<String, Double> getCostByResourceType() { return costByResourceType; }
    public void setCostByResourceType(Map<String, Double> costByResourceType) { this.costByResourceType = costByResourceType; }

    public Map<String, Long> getResourcesByStatus() { return resourcesByStatus; }
    public void setResourcesByStatus(Map<String, Long> resourcesByStatus) { this.resourcesByStatus = resourcesByStatus; }
}
