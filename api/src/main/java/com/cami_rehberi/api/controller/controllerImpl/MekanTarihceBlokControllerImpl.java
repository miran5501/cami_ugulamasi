package com.cami_rehberi.api.controller.controllerImpl;

import com.cami_rehberi.api.controller.IMekanTarihceBlokController;
import com.cami_rehberi.api.dto.request.MekanTarihceBlokEkleRequest;
import com.cami_rehberi.api.entity.MekanTarihceBlok;
import com.cami_rehberi.api.service.IMekanTarihceBlokService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/mekan-tarihce")
public class MekanTarihceBlokControllerImpl implements IMekanTarihceBlokController {

    @Autowired
    private IMekanTarihceBlokService mekanTarihceBlokService;

    @Override
    @PostMapping("/toplu-ekle")
    public List<MekanTarihceBlok> postMekanTarihceBlokEkle(
            @RequestBody MekanTarihceBlokEkleRequest mekanTarihceBlokEkleRequest) {
        return mekanTarihceBlokService.postMekanTarihceBlokEkle(mekanTarihceBlokEkleRequest);
    }
}
