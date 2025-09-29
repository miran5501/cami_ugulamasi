package com.cami_rehberi.api.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MekanKartResponse {

    private UUID id;

    private String ad;

    private String adres;

    private String kapakFotograf;

    private String bolgeAd;

    private Double enlem;

    private Double boylam;

    private Double mesafe;
}
