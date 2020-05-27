package no.dcat.service.permission;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@RequiredArgsConstructor
public final class SystemRootRole implements ResourceRole {
    static final String resourceType = "system";
    static final String resourceId = "root";
    private static final String adminRole = "admin";

    public String getResourceType() {
        return resourceType;
    }
    public String getResourceId() {
        return resourceId;
    }

    public Boolean matchPermission(String resourceType, String resourceId, String permission) {
        return matchResource(resourceType, resourceId) && adminRole.equals(permission);
    }
}
