package dev.julioperez.api.auth.infrastructure.repository.dao;


import dev.julioperez.api.auth.infrastructure.repository.model.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;


public interface UserDao extends JpaRepository<UserEntity, UUID> {
/*
    String queryCreateUser =
            "EXEC createUser @Username = :username , @Password = :password , @Email = :email , @Created = :created, @Enable = :enable , @IdRol = :idRol ";
    @Query(value = queryCreateUser, nativeQuery = true)
    UserEntity createUser(
            @Param("username") String username,
            @Param("password") String password,
            @Param("email") String email,
            @Param("created") Date created,
            @Param("enable") boolean enable,
            @Param("idRol") int idRol);
 */
/*
=======================================
    String queryUpdateEnableUser =
            "EXEC updateEnableUser @Id = :id , @Enable = :enable";
    @Query(value = queryUpdateEnableUser, nativeQuery = true)
    Optional<UserEntity> updateEnableUser(
            @Param("id") Long userid,
            @Param("enable") boolean enable);


 */
    //use saveAndFlush to update user
    /*
    =======================================
    String queryFindByUsername =
            "SELECT * FROM users WHERE username = :username ;";
    @Query(value = queryFindByUsername, nativeQuery = true)
    Optional<UserEntity> findByUsername(
            @Param("username") String username);


     */
    Optional<UserEntity> findFirstByEmail(String email);


    /*
    =======================================
    String queryFindById =
            "SELECT * FROM users " +
                    "WHERE id = :id ;";
    @Query(value = queryFindById, nativeQuery = true)
    Optional<UserEntity> findUserById(
            @Param("id") Long userid);
     */

}
