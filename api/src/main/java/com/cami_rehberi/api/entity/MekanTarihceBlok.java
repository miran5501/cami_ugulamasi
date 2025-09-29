package com.cami_rehberi.api.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class MekanTarihceBlok {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "mekan_id", nullable = false)
    private Mekan mekan;

    @Column(nullable = false, length = 5) // tr, en, el gibi
    private String dilKodu;

    @Column(columnDefinition = "TEXT", nullable = true) // paragraf uzun ve bazen boş olabilir
    private String paragraf;

    @Column(nullable = true) // resim olmayabilir
    private String resimUrl;

    @Column(nullable = false) // başlık mı değil mi bilgisi her zaman gelsin
    private Boolean isBaslik = false;

    @Column(nullable = false) // sıralama boş olmasın
    private Integer sira;
}

