package com.cami_rehberi.api.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MekanFotoDto {
    private String url;
    private Boolean isKapakFoto;
    private Integer sira;
}
