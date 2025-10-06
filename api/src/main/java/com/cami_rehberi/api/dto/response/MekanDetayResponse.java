package com.cami_rehberi.api.dto.response;

import com.cami_rehberi.api.dto.MekanFotoDto;
import com.cami_rehberi.api.entity.MekanFoto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MekanDetayResponse {

    private UUID id;

    private String ad;

    private String adres;

    private String bolgeAd;

    private Double enlem;

    private Double boylam;

    private Double mesafe;

    private List<MekanFotoDto> mekanFotoList;
}
