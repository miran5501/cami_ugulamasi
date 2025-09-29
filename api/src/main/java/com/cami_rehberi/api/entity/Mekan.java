package com.cami_rehberi.api.entity;

import java.util.UUID;

import com.cami_rehberi.api.enums.MekanTipi;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Mekan {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private UUID id;

    @Enumerated(EnumType.STRING)
    private MekanTipi tip;

    @ManyToOne
    @JoinColumn(name="bolge_id", nullable = false)
    private Bolge bolge;

    private Double enlem;

    private Double boylam;

    private String defaultAd;
}
