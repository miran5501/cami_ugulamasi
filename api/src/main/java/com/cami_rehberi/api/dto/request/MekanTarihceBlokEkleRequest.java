package com.cami_rehberi.api.dto.request;

import com.cami_rehberi.api.dto.MekanTarihceBlokDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MekanTarihceBlokEkleRequest {

    private UUID mekanId;

    private List<MekanTarihceBlokDto> bloklar;
}
