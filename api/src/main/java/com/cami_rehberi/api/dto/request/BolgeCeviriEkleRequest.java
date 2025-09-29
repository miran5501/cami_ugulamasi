package com.cami_rehberi.api.dto.request;

import com.cami_rehberi.api.dto.BolgeCeviriDto;
import com.cami_rehberi.api.entity.Bolge;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BolgeCeviriEkleRequest {

    private UUID bolgeId;

    private List<BolgeCeviriDto> ceviriler;
}
