package dev.julioperez.api.auth.infrastructure.repository.dao;

import dev.julioperez.api.auth.infrastructure.repository.model.VerificationTokenEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface VerificationTokenDao extends JpaRepository<VerificationTokenEntity, UUID> {



    String queryCreateToken =
            "EXEC createToken @Token = :token , @Id = :userid ";
//            "INSERT INTO TOKEN (token, userid) " +
//                    "VALUES( :token, :userid) ;";

    /*
    @Query(value = queryCreateToken, nativeQuery = true)
    Optional<VerificationTokenEntity> createToken(
            @Param("token") String token,
            @Param("userid") Long userid);
     */
    //Optional<VerificationTokenEntity>

    /*
    =======================================
    String queryFindByToken =
            "SELECT * FROM token WHERE token = :token ;";
    @Query(value = queryFindByToken, nativeQuery = true)
    Optional<VerificationTokenEntity> findByToken(@Param("token") String token);
 */
    Optional<VerificationTokenEntity> findFirstByToken(String token);

}
