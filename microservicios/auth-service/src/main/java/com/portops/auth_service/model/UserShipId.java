package com.portops.auth_service.model;

import java.io.Serializable;
import java.util.UUID;

import jakarta.persistence.Embeddable;

@Embeddable
public class UserShipId implements Serializable {

    private String keycloakId;
    private UUID shipId;

    public UserShipId() {}

    public UserShipId(String keycloakId, UUID shipId) {
        this.keycloakId = keycloakId;
        this.shipId = shipId;
    }

    public String getKeycloakId() {
        return keycloakId;
    }

    public void setKeycloakId(String keycloakId) {
        this.keycloakId = keycloakId;
    }

    public UUID getShipId() {
        return shipId;
    }

    public void setShipId(UUID shipId) {
        this.shipId = shipId;
    }

    
}
