package sn.free.myastreinte.web.rest;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import sn.free.myastreinte.security.jwt.JWTFilter;
import sn.free.myastreinte.security.jwt.TokenProvider;
import sn.free.myastreinte.web.rest.vm.LoginVM;

import javax.validation.Valid;

/*import sn.free.myastreinte.security.AstreinteRoles;*/

/**
 * Controller to authenticate users.
 */
@Data
@Slf4j
@RestController
@RequestMapping("/api")
public class UserJWTController {

    private final TokenProvider tokenProvider;

    private final AuthenticationManagerBuilder authenticationManagerBuilder;

    /*private final ActiveDirectoryLdapAuthenticationProvider adAuthProvider;
    private final UserService userService;
    private final UserDetailsService userDetailsService;*/


    /*public UserJWTController(TokenProvider tokenProvider, AuthenticationManagerBuilder authenticationManagerBuilder, ActiveDirectoryLdapAuthenticationProvider adAuthProvider, UserService userService, UserDetailsService userDetailsService) throws Exception {
        this.tokenProvider = tokenProvider;
        this.authenticationManagerBuilder = authenticationManagerBuilder;
        this.adAuthProvider = adAuthProvider;
        this.userService = userService;
        this.userDetailsService = userDetailsService;
        adAuthProvider.setSearchFilter("(&(objectClass=user)(userPrincipalName={0}))");
        this.authenticationManagerBuilder.userDetailsService(userDetailsService);
        this.authenticationManagerBuilder.authenticationProvider(adAuthProvider);
    }*/
    public UserJWTController(TokenProvider tokenProvider, AuthenticationManagerBuilder authenticationManagerBuilder) {
        this.tokenProvider = tokenProvider;
        this.authenticationManagerBuilder = authenticationManagerBuilder;
    }

    @PostMapping("/authenticate")
    public ResponseEntity<JWTToken> authorize(@Valid @RequestBody LoginVM loginVM) {

        UsernamePasswordAuthenticationToken authenticationToken =
            new UsernamePasswordAuthenticationToken(loginVM.getUsername(), loginVM.getPassword());

        Authentication authentication = authenticationManagerBuilder.getObject().authenticate(authenticationToken);
        SecurityContextHolder.getContext().setAuthentication(authentication);
        /*boolean rememberMe = (loginVM.isRememberMe() == null) ? false : loginVM.isRememberMe();

        if (authentication != null && !this.userService.getUserByLogin(loginVM.getUsername()).isPresent()) {
            LdapUserDetails userDetails = (LdapUserDetails) authentication.getPrincipal();
            final List<AstreinteRoles> roles = AstreinteRoles.allRoles.stream()
                .filter(mnpRoles -> userDetails.getAuthorities().contains(mnpRoles.getAuthority()))
                .collect(Collectors.toList());
            if(roles.isEmpty()) {
                log.error("{} filtered roles => {}", "JWT CONTROLLER", roles);
                return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
            }
            if(roles.contains(AstreinteRoles.ADMIN)){
                roles.add(AstreinteRoles.GESTIONNAIRE);
                roles.add(AstreinteRoles.EMPLOYE);
            }
            roles.add(AstreinteRoles.USER);
            this.userService.createUser(UserDTO.builder()
                .login(userDetails.getUsername())
                .firstName(userDetails.getDn()
                    .split(",")[0]
                    .replace("CN=", ""))
                .authorities(new HashSet<>(roles.stream()
                    .map(AstreinteRoles::getRole)
                    .collect(Collectors.toList())))
                .build());
        }
*/
        String jwt = tokenProvider.createToken(authentication, loginVM.isRememberMe());
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add(JWTFilter.AUTHORIZATION_HEADER, "Bearer " + jwt);
        return new ResponseEntity<>(new JWTToken(jwt), httpHeaders, HttpStatus.OK);
    }

    /**
     * Object to return as body in JWT Authentication.
     */
    static class JWTToken {

        private String idToken;

        JWTToken(String idToken) {
            this.idToken = idToken;
        }

        @JsonProperty("id_token")
        String getIdToken() {
            return idToken;
        }

        void setIdToken(String idToken) {
            this.idToken = idToken;
        }
    }
}
