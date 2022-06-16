package com.freudas.dev.repository;

import com.freudas.dev.model.User;
import com.freudas.dev.model.WorkProvider;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface WorkProviderRepository extends JpaRepository<WorkProvider, Long> {
    Optional<WorkProvider> findByDomain(String domain);
    WorkProvider findByOwner(User owner);
}
