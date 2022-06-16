package com.freudas.dev.repository;

import com.freudas.dev.model.AuthProvider;
import com.freudas.dev.model.User;
import com.freudas.dev.util.Constants;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByEmail(String email);

    Boolean existsByEmail(String email);

    List<User> findAllByProvider(AuthProvider provider);

    @Query("SELECT u FROM User u WHERE u.isAdminBlocked = false AND u.role = '"+ Constants.WORK_USER_ROLE +"'")
    List<User> findAllWorkUsers();
}
