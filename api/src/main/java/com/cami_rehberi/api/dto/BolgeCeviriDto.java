package com.cami_rehberi.api.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BolgeCeviriDto {
    private String dilKodu; // tr, en, el
    private String ad;
}
