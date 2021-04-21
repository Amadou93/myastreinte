package sn.free.myastreinte.security;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Slf4j
@Getter
public enum AstreinteRoles {

    ADMIN("G_TERTIO_ADMIN", "ROLE_ADMIN"),
    GESTIONNAIRE("G_TERTIO_AGENT", "ROLE_GESTIONNAIRE"),
    EMPLOYE("G_TERTIO", "ROLE_EMPLOYE"),
    USER("G_TERTIO", "ROLE_USER");

    public static List<AstreinteRoles> allRoles;

    static {
        List<AstreinteRoles> roles = new ArrayList<>(Arrays.asList(AstreinteRoles.values()));
        allRoles = Collections.unmodifiableList(roles);
    }

    private String activeDirectoryGroup;
    private String role;
    private GrantedAuthority authority;

    AstreinteRoles(String activeDirectoryGroup, String role) {
        this.activeDirectoryGroup = activeDirectoryGroup;
        this.role = role;
        this.authority = new SimpleGrantedAuthority(activeDirectoryGroup);
    }
}
