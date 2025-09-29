package com.cami_rehberi.api.controller.controllerImpl;

import com.cami_rehberi.api.controller.IMekanDetayController;
import com.cami_rehberi.api.dto.request.MekanDetayEkleRequest;
import com.cami_rehberi.api.entity.MekanDetay;
import com.cami_rehberi.api.service.IMekanDetayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/mekan-detay")
public class MekanDetayControllerImpl implements IMekanDetayController {

    @Autowired
    private IMekanDetayService mekanDetayService;

    @Override
    @PostMapping("/ekle-toplu")
    public List<MekanDetay> postMekanDetayEkleToplu(@RequestBody MekanDetayEkleRequest mekanDetayEkleRequest) {
        return mekanDetayService.postMekanDetayEkleToplu(mekanDetayEkleRequest);
    }
}
