package com.cami_rehberi.api.dto.request;

import com.cami_rehberi.api.dto.MekanDetayDto;
import com.cami_rehberi.api.entity.Mekan;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MekanDetayEkleRequest {

    private UUID mekanId;

    private List<MekanDetayDto> mekanDetayDtoList;
}
