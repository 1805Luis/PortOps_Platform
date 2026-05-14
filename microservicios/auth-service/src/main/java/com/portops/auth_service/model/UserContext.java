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
    private Set<UserAssignment> assignments;

    public UserContext() {}

    public UserContext(String keycloakId) {
        this.keycloakId = keycloakId;
    }
}