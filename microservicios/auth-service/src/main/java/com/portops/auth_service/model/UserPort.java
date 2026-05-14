package com.portops.auth_service.model;


import java.util.UUID;

import jakarta.persistence.*;

@Entity
@Table(name = "user_ports", schema = "auth")
public class UserPort {

    @EmbeddedId
    private UserPortId id;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("keycloakId")
    @JoinColumn(name = "keycloak_id")
    private UserContext user;

    public UserPort() {}

    public UserPort(UserContext user, UUID portId) {
        this.user = user;
        this.id = new UserPortId(user.getKeycloakId(), portId);
    }
}