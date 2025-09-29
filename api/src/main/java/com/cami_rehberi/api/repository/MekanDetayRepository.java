package com.cami_rehberi.api.repository;

import com.cami_rehberi.api.entity.MekanDetay;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface MekanDetayRepository extends JpaRepository<MekanDetay, UUID> {
    List<MekanDetay> findByDilKodu(String dilKodu);
}
