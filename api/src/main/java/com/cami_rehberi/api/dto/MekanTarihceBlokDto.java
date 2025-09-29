package com.cami_rehberi.api.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MekanTarihceBlokDto {
    private String dilKodu;
    private String paragraf;
    private String resimUrl;
    private Boolean isBaslik;
    private Integer sira;
}
