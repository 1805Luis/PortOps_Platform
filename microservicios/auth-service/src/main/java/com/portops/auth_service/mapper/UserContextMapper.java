package com.portops.auth_service.mapper;

import java.util.List;

import com.portops.auth_service.dtos.AssignmentDto;
import com.portops.auth_service.dtos.UserContextDto;
import com.portops.auth_service.model.UserAssignment;
import com.portops.auth_service.model.UserContext;

public class UserContextMapper {

    public UserContextDto toDto(UserContext entity, List<UserAssignment> assignments) {

        UserContextDto dto = new UserContextDto();

        dto.setUserId(entity.getKeycloakId());

        dto.setAssignments(
            assignments.stream()
                .map(this::toAssignmentDto)
                .toList()
        );

        return dto;
    }

    private AssignmentDto toAssignmentDto(UserAssignment a) {

        AssignmentDto dto = new AssignmentDto();

        dto.setEntityType(a.getEntityType());
        dto.setEntityId(a.getEntityId());
        dto.setRoleInContext(a.getRoleInContext());
        dto.setStatus(a.getStatus());

        return dto;
    }
}
