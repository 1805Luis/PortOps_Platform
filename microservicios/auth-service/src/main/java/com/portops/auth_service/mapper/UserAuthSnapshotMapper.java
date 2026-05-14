package com.portops.auth_service.mapper;

import java.time.Instant;
import java.util.stream.Collectors;

import com.portops.auth_service.dtos.AssignmentDto;
import com.portops.auth_service.dtos.UserAuthSnapshotDto;
import com.portops.auth_service.dtos.UserContextDto;
import com.portops.auth_service.model.EntityType;

public class UserAuthSnapshotMapper {

    public UserAuthSnapshotDto toSnapshot(UserContextDto dto) {

        UserAuthSnapshotDto snapshot = new UserAuthSnapshotDto();

        snapshot.setUserId(dto.getUserId());

        snapshot.setPortRoles(
            dto.getAssignments().stream()
                .filter(a -> a.getEntityType()== EntityType.PORT)
                .collect(Collectors.toMap(
                    AssignmentDto::getEntityId,
                    AssignmentDto::getRoleInContext
                ))
        );

        snapshot.setOrganizationRoles(
            dto.getAssignments().stream()
                .filter(a -> a.getEntityType() == EntityType.ORGANIZATION)
                .collect(Collectors.toMap(
                    AssignmentDto::getEntityId,
                    AssignmentDto::getRoleInContext
                ))
        );

        snapshot.setShipRoles(
            dto.getAssignments().stream()
                .filter(a -> a.getEntityType()== EntityType.SHIP)
                .collect(Collectors.toMap(
                    AssignmentDto::getEntityId,
                    AssignmentDto::getRoleInContext
                ))
        );


        snapshot.setExpiresAt(Instant.now().plusSeconds(120));

        return snapshot;
    }
}