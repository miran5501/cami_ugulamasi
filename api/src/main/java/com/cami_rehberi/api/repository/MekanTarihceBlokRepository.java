package com.cami_rehberi.api.repository;

import com.cami_rehberi.api.entity.Mekan;
import com.cami_rehberi.api.entity.MekanTarihceBlok;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface MekanTarihceBlokRepository extends JpaRepository<MekanTarihceBlok, UUID> {
    List<MekanTarihceBlok> findByMekanAndDilKoduOrderBySira(Mekan mekan, String dilKodu);
}
