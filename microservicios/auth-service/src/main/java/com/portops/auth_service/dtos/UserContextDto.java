package com.portops.auth_service.dtos;

import java.time.Instant;
import java.util.List;
import java.util.Set;
import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserContextDto {

    private String userId;

    private List<String> roles;

    private List<AssignmentDto> assignments;

    private Set<UUID> activePorts;

    private Set<UUID> activeShips;

    private Set<UUID> activeOrganizations;

    private Instant lastUpdated;
}