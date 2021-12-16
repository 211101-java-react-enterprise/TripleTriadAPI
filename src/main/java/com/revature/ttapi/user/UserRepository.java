package com.revature.ttapi.user;

import com.revature.ttapi.user.models.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface UserRepository extends JpaRepository<AppUser, UUID> {

    Optional<AppUser> findByUsername(String username);

    void deleteByUsername(String username);

    @Query("from AppUser au where au.username = :username and au.password = :password")
    Optional<AppUser> findByUsernameAndPassword(String username, String password);
}
