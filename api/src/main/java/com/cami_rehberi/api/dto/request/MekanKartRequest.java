package com.cami_rehberi.api.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MekanKartRequest {

    private String dilKodu;

    private Double enlem;

    private Double boylam;

    public String getDilKodu() {
        return (dilKodu == null || dilKodu.isBlank()) ? "tr" : dilKodu;
    }
}
