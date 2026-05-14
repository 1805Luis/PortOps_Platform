package com.portops.auth_service.dtos;

import java.time.Instant;
import java.util.UUID;

import com.portops.auth_service.model.EntityType;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AssignmentDto {

    private EntityType entityType; // PORT / SHIP / ORGANIZATION

    private UUID entityId;

    private String roleInContext;

    private String status; // ACTIVE / SUSPENDED

    private Instant startDate;

    private Instant endDate;
}