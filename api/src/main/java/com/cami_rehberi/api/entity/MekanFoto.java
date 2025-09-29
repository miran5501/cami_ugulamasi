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
public class MekanFoto {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @ManyToOne
    @JoinColumn(name="mekan_id", nullable = false)
    private Mekan mekan;

    private String url;

    private Boolean isKapakFoto;

    private Integer sira;
}
