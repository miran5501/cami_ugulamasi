package com.cami_rehberi.api.dto.request;

import com.cami_rehberi.api.enums.MekanTipi;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MekanEkleRequest {

    private MekanTipi tip;

    private UUID bolgeId;

    private Double enlem;

    private Double boylam;

    private String defaultAd;
}
