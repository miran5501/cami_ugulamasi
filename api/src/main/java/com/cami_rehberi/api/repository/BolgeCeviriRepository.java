package com.cami_rehberi.api.repository;

import com.cami_rehberi.api.entity.BolgeCeviri;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface BolgeCeviriRepository extends JpaRepository<BolgeCeviri, UUID> {
    List<BolgeCeviri> findByDilKodu(String dilKodu);
}
