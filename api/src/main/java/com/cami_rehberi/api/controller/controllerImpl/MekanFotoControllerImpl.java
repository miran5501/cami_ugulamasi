package com.cami_rehberi.api.controller.controllerImpl;

import com.cami_rehberi.api.controller.IMekanFotoController;
import com.cami_rehberi.api.dto.request.MekanFotoEkleRequest;
import com.cami_rehberi.api.entity.MekanFoto;
import com.cami_rehberi.api.service.IMekanFotoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/mekan-foto")
public class MekanFotoControllerImpl implements IMekanFotoController {

    @Autowired
    private IMekanFotoService mekanFotoService;

    @Override
    @PostMapping("/ekle")
    public List<MekanFoto> postMekanFotoEkle(@RequestBody MekanFotoEkleRequest request) {
        return mekanFotoService.postMekanFotoEkle(request);
    }
}
