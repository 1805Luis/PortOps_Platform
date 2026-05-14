package com.portops.auth_service.model;

import java.util.UUID;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
import jakarta.persistence.Table;


@Entity
@Table(name = "user_organization", schema = "auth")
public class UserOrganization {
    @EmbeddedId
    private UserOrganizationId id;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("keycloakId")
    @JoinColumn(name = "keycloak_id")
    private UserContext user;

    public UserOrganization() {}

    public UserOrganization(UserContext user, UUID organizationId) {
        this.user = user;
        this.id = new UserOrganizationId(user.getKeycloakId(), organizationId);
    }

    public UserOrganizationId getId() {
        return id;
    }

    public UserContext getUser() {
        return user;
    }
}