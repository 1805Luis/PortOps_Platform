package com.portops.auth_service.repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.portops.auth_service.model.EntityType;
import com.portops.auth_service.model.UserAssignment;

@Repository
public interface UserAssignmentRepository extends JpaRepository<UserAssignment, UUID> {

    List<UserAssignment> findByUserId(String userId);

    List<UserAssignment> findByUserIdAndStatus(String userId, String status);

    List<UserAssignment> findByUserIdAndEntityTypeAndStatus(
        String userId,
        EntityType entityType,
        String status
    );

    List<UserAssignment> findByEntityTypeAndEntityIdAndStatus(
        EntityType entityType,
        UUID entityId,
        String status
    );
}
