package com.cami_rehberi.api.controller.controllerImpl;

import com.cami_rehberi.api.common.response.ApiResponse;
import com.cami_rehberi.api.controller.IMekanController;
import com.cami_rehberi.api.dto.request.MekanEkleRequest;
import com.cami_rehberi.api.dto.request.MekanKartRequest;
import com.cami_rehberi.api.dto.response.MekanDetayResponse;
import com.cami_rehberi.api.dto.response.MekanKartResponse;
import com.cami_rehberi.api.dto.response.MekanTarihceResponse;
import com.cami_rehberi.api.entity.Mekan;
import com.cami_rehberi.api.service.IMekanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/mekan")
public class MekanControllerImpl implements IMekanController {

    @Autowired
    private IMekanService mekanService;

    @Override
    @PostMapping("/kartlar")
    public ResponseEntity<ApiResponse<List<MekanKartResponse>>> getAllMekanKartResponse(
            @RequestBody MekanKartRequest request) {
        List<MekanKartResponse> kartlar = mekanService.getAllMekanKartResponse(request);
        return ResponseEntity.ok(ApiResponse.success(kartlar));
    }

    @Override
    @PostMapping("/ekle")
    public ResponseEntity<ApiResponse<Mekan>> postMekanEkle(@RequestBody MekanEkleRequest mekanEkleRequest) {
        Mekan mekan = mekanService.postMekanEkle(mekanEkleRequest);
        return ResponseEntity.ok(ApiResponse.success(mekan, "Mekan başarıyla eklendi"));
    }

    @Override
    @GetMapping("/getir-all")
    public ResponseEntity<ApiResponse<List<Mekan>>> getAllMekanGetir() {
        List<Mekan> mekanlar = mekanService.getAllMekanGetir();
        return ResponseEntity.ok(ApiResponse.success(mekanlar));
    }

    @Override
    @PostMapping("/{id}/detay")
    public ResponseEntity<ApiResponse<MekanDetayResponse>> getMekanDetayGetir(
            @RequestBody MekanKartRequest mekanKartRequest,
            @PathVariable UUID id) {
        MekanDetayResponse detay = mekanService.getMekanDetayGetir(mekanKartRequest, id);
        return ResponseEntity.ok(ApiResponse.success(detay));
    }

    @GetMapping("/{id}/tarihce")
    public ResponseEntity<ApiResponse<List<MekanTarihceResponse>>> getMekanDetayTarihce(
            @PathVariable UUID id,
            @RequestParam(defaultValue = "tr") String dilKodu
    ) {
        List<MekanTarihceResponse> tarihce = mekanService.getMekanDetayTarihce(id, dilKodu);
        return ResponseEntity.ok(ApiResponse.success(tarihce));
    }
}
