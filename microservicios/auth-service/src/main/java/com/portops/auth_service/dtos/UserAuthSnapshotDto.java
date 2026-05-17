package com.portops.auth_service.dtos;

import java.util.Map;
import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserAuthSnapshotDto {

    private String userId;

    private Map<UUID, String> organizationRoles;

    private Map<UUID, String> portRoles;

    private Map<UUID, String> shipRoles;

}