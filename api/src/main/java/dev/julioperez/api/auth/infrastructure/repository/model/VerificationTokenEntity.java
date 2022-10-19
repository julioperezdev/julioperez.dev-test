package dev.julioperez.api.auth.infrastructure.repository.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(schema = "AUTH", name = "VERIFICATION_TOKEN")
public class VerificationTokenEntity {

    @Id
    @Type(type = "org.hibernate.type.UUIDCharType")
    private UUID id;

    @Column(name = "TOKEN", nullable = false, unique = true)
    @Type(type = "org.hibernate.type.UUIDCharType")
    private UUID token;

    @ManyToOne(optional = false, cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "FK_ID_USER", nullable = false)
    private UserEntity user;

    public VerificationTokenEntity(UUID token, UserEntity user) {
        this.token = token;
        this.user = user;
    }
}
