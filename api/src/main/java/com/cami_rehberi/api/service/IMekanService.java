package com.cami_rehberi.api.service;

import com.cami_rehberi.api.dto.request.MekanEkleRequest;
import com.cami_rehberi.api.dto.request.MekanKartRequest;
import com.cami_rehberi.api.dto.response.MekanDetayResponse;
import com.cami_rehberi.api.dto.response.MekanKartResponse;
import com.cami_rehberi.api.dto.response.MekanTarihceResponse;
import com.cami_rehberi.api.entity.Mekan;

import java.util.List;
import java.util.UUID;

public interface IMekanService {
    public List<MekanKartResponse> getAllMekanKartResponse(MekanKartRequest mekanKartRequest);

    public Mekan postMekanEkle(MekanEkleRequest mekanEkleRequest);

    public List<Mekan> getAllMekanGetir();

    public MekanDetayResponse getMekanDetayGetir(MekanKartRequest mekanKartRequest, UUID id);

    public List<MekanTarihceResponse> getMekanDetayTarihce(UUID id, String dilKodu);
}
