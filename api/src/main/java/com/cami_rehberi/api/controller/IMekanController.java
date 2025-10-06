package com.cami_rehberi.api.controller;

import com.cami_rehberi.api.common.response.ApiResponse;
import com.cami_rehberi.api.dto.request.MekanEkleRequest;
import com.cami_rehberi.api.dto.request.MekanKartRequest;
import com.cami_rehberi.api.dto.response.MekanDetayResponse;
import com.cami_rehberi.api.dto.response.MekanKartResponse;
import com.cami_rehberi.api.dto.response.MekanTarihceResponse;
import com.cami_rehberi.api.entity.Mekan;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.UUID;

public interface IMekanController {

    ResponseEntity<ApiResponse<List<MekanKartResponse>>> getAllMekanKartResponse(MekanKartRequest mekanKartRequest);

    ResponseEntity<ApiResponse<Mekan>> postMekanEkle(MekanEkleRequest mekanEkleRequest);

    ResponseEntity<ApiResponse<List<Mekan>>> getAllMekanGetir();

    ResponseEntity<ApiResponse<MekanDetayResponse>> getMekanDetayGetir(MekanKartRequest mekanKartRequest, UUID id);

    ResponseEntity<ApiResponse<List<MekanTarihceResponse>>> getMekanDetayTarihce(UUID id, String dilKodu);
}
