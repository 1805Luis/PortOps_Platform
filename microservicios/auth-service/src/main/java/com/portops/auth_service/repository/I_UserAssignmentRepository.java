package com.portops.auth_service.repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.portops.auth_service.model.EntityType;
import com.portops.auth_service.model.UserAssignment;

@Repository
public interface I_UserAssignmentRepository
        extends JpaRepository<UserAssignment, UUID> {

    List<UserAssignment> findByUserKeycloakId(String keycloakId);

    List<UserAssignment> findByUserKeycloakIdAndStatus(String keycloakId, String status);

    List<UserAssignment> findByUserKeycloakIdAndEntityTypeAndStatus(String keycloakId, EntityType entityType, String status);

    List<UserAssignment> findByEntityTypeAndEntityIdAndStatus(EntityType entityType, UUID entityId, String status);

    boolean existsByUserKeycloakIdAndEntityTypeAndEntityIdAndStatus(String keycloakId, EntityType entityType, UUID entityId, String status );
}