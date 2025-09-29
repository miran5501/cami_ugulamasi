package com.cami_rehberi.api.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MekanDetayDto {

    private String dilKodu;

    private String ad;

    private String kisaAciklama;

    private String adres;
}
