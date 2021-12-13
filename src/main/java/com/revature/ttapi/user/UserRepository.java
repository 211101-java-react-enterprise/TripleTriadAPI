package com.revature.ttapi.user;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends CrudRepository<AppUser, String> {

    Optional<AppUser> findAppUserByUsername(String username);
    Optional<AppUser> findAppUserByEmail(String email);

    @Query("from AppUser au where au.username = :username and au.password = :password")
    Optional<AppUser> findUserByUsernameAndPassword(String username, String password);
}
