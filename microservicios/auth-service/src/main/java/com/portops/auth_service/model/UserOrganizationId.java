package com.portops.auth_service.model;

import jakarta.persistence.Embeddable;
import java.io.Serializable;
import java.util.UUID;

@Embeddable
public class UserOrganizationId implements Serializable {

    private String keycloakId;
    private UUID organizationId;

    public UserOrganizationId() {}

    public UserOrganizationId(String keycloakId, UUID organizationId) {
        this.keycloakId = keycloakId;
        this.organizationId = organizationId;
    }

    public String getKeycloakId() {
        return keycloakId;
    }

    public UUID getOrganizationId() {
        return organizationId;
    }

    public void setKeycloakId(String keycloakId) {
        this.keycloakId = keycloakId;
    }

    public void setOrganizationId(UUID organizationId) {
        this.organizationId = organizationId;
    }

    
}
