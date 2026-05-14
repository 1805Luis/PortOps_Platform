package com.portops.auth_service.model;

import java.io.Serializable;
import java.util.UUID;

import jakarta.persistence.Embeddable;

@Embeddable
public class UserPortId implements Serializable {

    private String keycloakId;
    private UUID portId;

    public UserPortId() {}

    public UserPortId(String keycloakId, UUID portId) {
        this.keycloakId = keycloakId;
        this.portId = portId;
    }

    public String getKeycloakId() {
        return keycloakId;
    }

    public void setKeycloakId(String keycloakId) {
        this.keycloakId = keycloakId;
    }

    public UUID getPortId() {
        return portId;
    }

    public void setPortId(UUID portId) {
        this.portId = portId;
    }

    
}