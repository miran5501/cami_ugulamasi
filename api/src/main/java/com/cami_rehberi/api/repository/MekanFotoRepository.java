package com.cami_rehberi.api.repository;

import com.cami_rehberi.api.entity.MekanFoto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface MekanFotoRepository extends JpaRepository<MekanFoto, UUID> {
    Optional<MekanFoto> findFirstByMekanIdAndIsKapakFotoTrue(UUID mekanId);
}
