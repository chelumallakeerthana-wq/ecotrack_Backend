package com.ecotrack.controller;

import com.ecotrack.dto.DashboardSummary;
import com.ecotrack.service.CloudResourceService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * REST Controller for Dashboard aggregated data.
 * 
 * Provides a single endpoint that returns summary statistics
 * used by the frontend dashboard charts and cards.
 */
@RestController
@RequestMapping("/api/dashboard")
public class DashboardController {

    private final CloudResourceService service;

    public DashboardController(CloudResourceService service) {
        this.service = service;
    }

    /**
     * GET /api/dashboard/summary
     * Returns aggregated statistics: totals, costs by provider,
     * carbon by provider, costs by resource type, etc.
     */
    @GetMapping("/summary")
    public ResponseEntity<DashboardSummary> getDashboardSummary() {
        return ResponseEntity.ok(service.getDashboardSummary());
    }
}
