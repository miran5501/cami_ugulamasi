package com.cami_rehberi.api.repository;

import com.cami_rehberi.api.entity.Bolge;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface BolgeRepository extends JpaRepository<Bolge, UUID> {
}
