package dev.julioperez.api.auth.infrastructure.repository.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.Calendar;
import java.util.UUID;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(schema = "AUTH", name = "REFRESH_TOKEN")
public class RefreshTokenEntity {
    @Id
    //@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "REFRESH_TOKEN_SEQUENCE")
    //@SequenceGenerator(name = "REFRESH_TOKEN_SEQUENCE", allocationSize = 1)
    @Type(type = "org.hibernate.type.UUIDCharType")
    private UUID id;
    @Column(name = "TOKEN", nullable = false, unique = true)
    private String token;

    @Column(name = "CREATED_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Calendar createdDate;

    public RefreshTokenEntity(String token, Calendar createdDate) {
        this.token = token;
        this.createdDate = createdDate;
    }
}
