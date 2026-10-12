package com.ecotrack.controller;

import com.ecotrack.model.CloudResource;
import com.ecotrack.service.CloudResourceService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

/**
 * REST Controller for Cloud Resource CRUD operations.
 * 
 * All endpoints are prefixed with /api/resources.
 * This controller handles creating, reading, updating, and deleting
 * cloud resources.
 */
@RestController
@RequestMapping("/api/resources")
public class CloudResourceController {

    private final CloudResourceService service;

    public CloudResourceController(CloudResourceService service) {
        this.service = service;
    }

    /**
     * GET /api/resources
     * Returns a list of all cloud resources.
     */
    @GetMapping
    public ResponseEntity<List<CloudResource>> getAllResources() {
        return ResponseEntity.ok(service.getAllResources());
    }

    /**
     * GET /api/resources/{id}
     * Returns a single resource by its ID.
     */
    @GetMapping("/{id}")
    public ResponseEntity<CloudResource> getResourceById(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(service.getResourceById(id));
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    /**
     * POST /api/resources
     * Creates a new cloud resource.
     * The @Valid annotation triggers bean validation on the request body.
     */
    @PostMapping
    public ResponseEntity<CloudResource> createResource(@Valid @RequestBody CloudResource resource) {
        CloudResource created = service.createResource(resource);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    /**
     * PUT /api/resources/{id}
     * Updates an existing cloud resource.
     */
    @PutMapping("/{id}")
    public ResponseEntity<CloudResource> updateResource(
            @PathVariable Long id,
            @Valid @RequestBody CloudResource resource) {
        try {
            return ResponseEntity.ok(service.updateResource(id, resource));
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    /**
     * DELETE /api/resources/{id}
     * Deletes a cloud resource.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteResource(@PathVariable Long id) {
        try {
            service.deleteResource(id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    /**
     * GET /api/resources/by-provider/{provider}
     * Returns resources filtered by cloud provider (AWS, Azure, GCP).
     */
    @GetMapping("/by-provider/{provider}")
    public ResponseEntity<List<CloudResource>> getByProvider(@PathVariable String provider) {
        return ResponseEntity.ok(service.getByProvider(provider));
    }

    /**
     * GET /api/resources/by-status/{status}
     * Returns resources filtered by status (Active, Idle, Terminated).
     */
    @GetMapping("/by-status/{status}")
    public ResponseEntity<List<CloudResource>> getByStatus(@PathVariable String status) {
        return ResponseEntity.ok(service.getByStatus(status));
    }
}
