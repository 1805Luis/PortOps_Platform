package com.portops.auth_service.model;

import jakarta.persistence.*;
import java.util.Set;

@Entity
@Table(name = "user_context", schema = "auth")
public class UserContext {

    @Id
    @Column(name = "keycloak_id", length = 100)
    private String keycloakId;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<UserOrganization> organizations;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<UserPort> ports;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<UserShip> ships;

    public UserContext() {}

    public UserContext(String keycloakId) {
        this.keycloakId = keycloakId;
    }

    public String getKeycloakId() {
        return keycloakId;
    }

    public void setKeycloakId(String keycloakId) {
        this.keycloakId = keycloakId;
    }

    public Set<UserOrganization> getOrganizations() {
        return organizations;
    }

    public Set<UserPort> getPorts() {
        return ports;
    }

    public Set<UserShip> getShips() {
        return ships;
    }
}
