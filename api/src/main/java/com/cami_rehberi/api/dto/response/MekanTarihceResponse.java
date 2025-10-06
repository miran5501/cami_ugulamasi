package com.cami_rehberi.api.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MekanTarihceResponse {
    private String paragraf;
    private String resimUrl;
    private Boolean isBaslik;
    private Integer sira;
}
