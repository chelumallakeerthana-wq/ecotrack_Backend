package com.ecotrack.repository;

import com.ecotrack.model.CloudResource;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

/**
 * Repository interface for CloudResource database operations.
 * 
 * Spring Data JPA automatically provides CRUD methods (save, findAll,
 * findById, delete, etc.). We just define custom query methods below
 * and Spring generates the SQL for us based on the method name.
 */
@Repository
public interface CloudResourceRepository extends JpaRepository<CloudResource, Long> {

    /** Find all resources from a specific cloud provider (e.g., "AWS") */
    List<CloudResource> findByProvider(String provider);

    /** Find all resources with a specific status (e.g., "Active") */
    List<CloudResource> findByStatus(String status);

    /** Find all resources of a specific type (e.g., "VM") */
    List<CloudResource> findByResourceType(String resourceType);
}
