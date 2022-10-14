package dev.julioperez.api.auth.infrastructure.repository.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Calendar;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "REFRESH_TOKEN")
public class RefreshTokenEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "REFRESH_TOKEN_SEQUENCE")
    @SequenceGenerator(name = "REFRESH_TOKEN_SEQUENCE", allocationSize = 1)
    private Long id;
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
