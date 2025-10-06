package com.cami_rehberi.api.repository;

import com.cami_rehberi.api.entity.Mekan;
import com.cami_rehberi.api.entity.MekanDetay;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface MekanDetayRepository extends JpaRepository<MekanDetay, UUID> {
    // Liste ekranında dil bazlı tüm detaylar
    List<MekanDetay> findByDilKodu(String dilKodu);

    // Detay ekranında tek mekana göre (id + dil)
    Optional<MekanDetay> findByMekanAndDilKodu(Mekan mekan, String dilKodu);
}
