package com.cami_rehberi.api.controller.controllerImpl;

import com.cami_rehberi.api.controller.IBolgeController;
import com.cami_rehberi.api.entity.Bolge;
import com.cami_rehberi.api.service.IBolgeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/bolge")
public class BolgeControllerImpl implements IBolgeController {

    @Autowired
    private IBolgeService bolgeService;

    @Override
    @PostMapping("/ekle")
    public Bolge postBolgeEkle(@RequestParam String ad) {
        // İş mantığını Service'e delege ediyoruz
        return bolgeService.postBolgeEkle(ad);
    }

    @Override
    @GetMapping("/getir-all")
    public List<Bolge> getTumBolgelerGetir() {
        return bolgeService.getTumBolgelerGetir();
    }
}
