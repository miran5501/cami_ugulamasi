package com.cami_rehberi.api.controller.controllerImpl;

import com.cami_rehberi.api.controller.IMekanController;
import com.cami_rehberi.api.dto.request.MekanEkleRequest;
import com.cami_rehberi.api.dto.request.MekanKartRequest;
import com.cami_rehberi.api.dto.response.MekanKartResponse;
import com.cami_rehberi.api.entity.Mekan;
import com.cami_rehberi.api.service.IMekanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/mekan")
public class MekanControllerImpl implements IMekanController {

    @Autowired
    private IMekanService mekanService;

    @Override
    @PostMapping("/kartlar")
    public List<MekanKartResponse> getAllMekanKartResponse(
            @RequestBody MekanKartRequest request) {
        return mekanService.getAllMekanKartResponse(request);
    }

    @Override
    @PostMapping("/ekle")
    public Mekan postMekanEkle(@RequestBody MekanEkleRequest mekanEkleRequest) {
        return mekanService.postMekanEkle(mekanEkleRequest);
    }

    @Override
    @GetMapping("/getir-all")
    public List<Mekan> getAllMekanGetir() {
        return mekanService.getAllMekanGetir();
    }

}

