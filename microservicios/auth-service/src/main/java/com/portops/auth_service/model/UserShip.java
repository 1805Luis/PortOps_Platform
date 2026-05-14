package com.portops.auth_service.model;

import java.util.UUID;

import jakarta.persistence.*;


@Entity
@Table(name = "user_ships", schema = "auth")
public class UserShip {

    @EmbeddedId
    private UserShipId id;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("keycloakId")
    @JoinColumn(name = "keycloak_id")
    private UserContext user;

    public UserShip() {}

    public UserShip(UserContext user, UUID shipId) {
        this.user = user;
        this.id = new UserShipId(user.getKeycloakId(), shipId);
    }
}
