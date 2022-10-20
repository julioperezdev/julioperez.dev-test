package dev.julioperez.api.auth.infrastructure.app.security;

import dev.julioperez.api.auth.domain.model.LoginRequest;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
@NoArgsConstructor
public class ManagerAuthenticator /*implements InitializingBean */{

    private AuthenticationManager authenticationManager;
    //private final ApplicationContext applicationContext;


    public Authentication authenticateByEmailAndPassword(LoginRequest loginRequest) throws Exception{
        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken =
                new UsernamePasswordAuthenticationToken(
                loginRequest.getEmail(),
                loginRequest.getPassword());
        return authenticationManager.authenticate(usernamePasswordAuthenticationToken);
    }

    public void setAuthenticationToSecurityContext(Authentication authentication){
        SecurityContextHolder.getContext().setAuthentication(authentication);
    }

//    @Override
//    public void afterPropertiesSet() throws Exception {
//        authenticationManager = applicationContext.getBean(AuthenticationManager.class);
//    }
}
