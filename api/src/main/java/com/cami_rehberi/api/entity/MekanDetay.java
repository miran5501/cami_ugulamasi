package com.cami_rehberi.api.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class MekanDetay {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private UUID id;

    @ManyToOne
    @JoinColumn(name="mekan_id", nullable = false)
    private Mekan mekan;

    private String dilKodu;

    private String ad;

    private String kisaAciklama;

    private String adres;
}
