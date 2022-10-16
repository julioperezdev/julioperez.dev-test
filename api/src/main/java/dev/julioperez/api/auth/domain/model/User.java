package dev.julioperez.api.auth.domain.model;

import dev.julioperez.api.shared.application.getProperty.service.GetPropertyService;

import java.util.Calendar;
import java.util.Objects;
import java.util.UUID;

public class User {

    private UUID id;
    private String password;
    private String email;
    private Calendar created;
    private Boolean enable;
    private UUID idRol;

    public static final UUID USER_ID_ROL = UUID.fromString(GetPropertyService.getPropertyByKey("julioperez.user.rol.uuid"));

    public User(UUID id, String password, String email, Calendar created, Boolean enable, UUID idRol) {
        this.id = id;
        this.password = password;
        this.email = email;
        this.created = created;
        this.enable = enable;
        this.idRol = idRol;
    }

    public User( String password, String email, Calendar created, Boolean enable, UUID idRol) {
        this.password = password;
        this.email = email;
        this.created = created;
        this.enable = enable;
        this.idRol = idRol;
    }

    public UUID getId() {
        return id;
    }

    public void setUserId(UUID id) {
        this.id = id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Calendar getCreated() {
        return created;
    }

    public void setCreated(Calendar created) {
        this.created = created;
    }

    public Boolean isEnable() {
        return enable;
    }

    public void setEnable(Boolean enable) {
        this.enable = enable;
    }

    public UUID getIdRol() {
        return idRol;
    }

    public void setIdRol(UUID idRol) {
        this.idRol = idRol;
    }

    public boolean isNotEqualLikeUserIdRol(UUID idRol){
        return !Objects.equals(USER_ID_ROL,idRol);
    }
}

