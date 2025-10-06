package com.cami_rehberi.api.repository;

import com.cami_rehberi.api.entity.Bolge;
import com.cami_rehberi.api.entity.BolgeCeviri;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface BolgeCeviriRepository extends JpaRepository<BolgeCeviri, UUID> {
    // Liste ekranında dil bazlı tüm bölgeler
    List<BolgeCeviri> findByDilKodu(String dilKodu);

    // Detay ekranında belirli bölge + dil
    Optional<BolgeCeviri> findByBolgeAndDilKodu(Bolge bolge, String dilKodu);
}
