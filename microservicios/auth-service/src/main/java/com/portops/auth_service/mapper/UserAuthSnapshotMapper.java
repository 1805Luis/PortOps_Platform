package com.portops.auth_service.mapper;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.portops.auth_service.dtos.UserAuthSnapshotDto;
import com.portops.auth_service.model.EntityType;
import com.portops.auth_service.model.UserAssignment;

@Component
public class UserAuthSnapshotMapper {

    public UserAuthSnapshotDto toSnapshot(List<UserAssignment> assignments, String userId) {

        UserAuthSnapshotDto snapshot = new UserAuthSnapshotDto();

        snapshot.setUserId(userId);

        snapshot.setPortRoles(
            assignments.stream()
                .filter(a -> a.getEntityType() == EntityType.PORT)
                .collect(Collectors.toMap(
                    UserAssignment::getEntityId,
                    UserAssignment::getRoleInContext
                ))
        );

        snapshot.setOrganizationRoles(
            assignments.stream()
                .filter(a -> a.getEntityType() == EntityType.ORGANIZATION)
                .collect(Collectors.toMap(
                    UserAssignment::getEntityId,
                    UserAssignment::getRoleInContext
                ))
        );

        snapshot.setShipRoles(
            assignments.stream()
                .filter(a -> a.getEntityType() == EntityType.SHIP)
                .collect(Collectors.toMap(
                    UserAssignment::getEntityId,
                    UserAssignment::getRoleInContext
                ))
        );

        return snapshot;
    }
}