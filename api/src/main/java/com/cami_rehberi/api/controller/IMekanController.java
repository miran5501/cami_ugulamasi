package com.cami_rehberi.api.controller;

import com.cami_rehberi.api.dto.request.MekanEkleRequest;
import com.cami_rehberi.api.dto.request.MekanKartRequest;
import com.cami_rehberi.api.dto.response.MekanKartResponse;
import com.cami_rehberi.api.entity.Mekan;

import java.util.List;

public interface IMekanController {

    public List<MekanKartResponse> getAllMekanKartResponse(MekanKartRequest mekanKartRequest);

    public Mekan postMekanEkle(MekanEkleRequest mekanEkleRequest);

    public List<Mekan> getAllMekanGetir();
}
