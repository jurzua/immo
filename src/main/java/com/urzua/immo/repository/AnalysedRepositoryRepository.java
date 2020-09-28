package com.urzua.immo.repository;

import com.urzua.immo.model.PersistentRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AnalysedRepositoryRepository extends JpaRepository<PersistentRepository, Long> {
}
