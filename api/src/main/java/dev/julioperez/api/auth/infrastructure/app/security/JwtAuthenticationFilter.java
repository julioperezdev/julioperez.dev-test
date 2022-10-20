package dev.julioperez.api.auth.infrastructure.app.security;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import dev.julioperez.api.auth.domain.exception.InvalidTokenToUseApi;
import dev.julioperez.api.shared.infrastructure.delivery.RestExceptionInterceptor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private static final String BEARER = "Bearer ";
    private static final String AUTHORIZATION = "Authorization";
    @Value("${julioperez.jwt.filter.1}")
    private String URI_1;
    @Value("${julioperez.jwt.filter.2}")
    private String URI_2;
    @Value("${julioperez.jwt.filter.3}")
    private String URI_3;

    private final JwtProvider jwtProvider;
    private final UserDetailsService userDetailsService;
    public JwtAuthenticationFilter(JwtProvider jwtProvider, UserDetailsService userDetailsService) {
        this.jwtProvider = jwtProvider;
        this.userDetailsService = userDetailsService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {
        String jwt = getJwtFromRequest(request);
        if(!isPermitURL(request) && (!StringUtils.hasText(jwt) || !jwtProvider.validateToken(jwt))){
            invalidTokenToUseApiHandleException(response);
            return;
        }
        if (isPermitURL(request) && !StringUtils.hasText(jwt) || !jwtProvider.validateToken(jwt)){
            filterChain.doFilter(request, response);
            return;
        }
        if (StringUtils.hasText(jwt) && jwtProvider.validateToken(jwt)) {
            String username = jwtProvider.getUsernameFromJwt(jwt);
            UserDetails userDetails = userDetailsService.loadUserByUsername(username);
            UsernamePasswordAuthenticationToken authentication =
                    new UsernamePasswordAuthenticationToken(
                            userDetails,
                            null,
                            userDetails.getAuthorities());
            authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
            SecurityContextHolder.getContext().setAuthentication(authentication);
            filterChain.doFilter(request, response);
        }
    }
    private void invalidTokenToUseApiHandleException(ServletResponse response) throws IOException {
        RestExceptionInterceptor restExceptionInterceptor = new RestExceptionInterceptor();
        ResponseEntity responseEntity = restExceptionInterceptor.throwInvalidTokenToUseApi(new InvalidTokenToUseApi());
        responseException(response,responseEntity);
    }
    private void responseException(ServletResponse response, ResponseEntity<?> responseEntity) throws IOException{
        ((HttpServletResponse) response).setStatus(responseEntity.getStatusCodeValue());
        byte[] responseToSend = restResponseBytes(responseEntity.getBody());
        response.getOutputStream().write(responseToSend);
        response.setContentType("application/json");
        return;
    }
    private byte[] restResponseBytes(Object object) throws JsonProcessingException {
        String serialized = new ObjectMapper().writeValueAsString(object);
        return serialized.getBytes();
    }
    private String getJwtFromRequest(HttpServletRequest request){

        String bearerToken = request.getHeader(AUTHORIZATION);
        if (StringUtils.hasText(bearerToken) && bearerToken.startsWith(BEARER)) {
            return bearerToken.substring(7);
        }
        return bearerToken;
    }
    private boolean isPermitURL(HttpServletRequest request){
        List<String> urlWithPermission = new ArrayList<>();
        urlWithPermission.add(URI_1);
        urlWithPermission.add(URI_2);
        urlWithPermission.add(URI_3);
        String requestURL = String.valueOf(request.getRequestURI());
        return urlWithPermission.stream().anyMatch(requestURL::equals);
    }
}