package dev.julioperez.api.auth.infrastructure.repository.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Entity
@Data
@Table(schema = "AUTH", name = "USER_ROL")
public class UserRolEntity {

    @Id
    @Type(type = "org.hibernate.type.UUIDCharType")
    private UUID id;
    @Column(unique = true, nullable = false)
    private String description;
}
