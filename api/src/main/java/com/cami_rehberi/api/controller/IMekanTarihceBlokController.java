package com.cami_rehberi.api.controller;

import com.cami_rehberi.api.dto.request.MekanTarihceBlokEkleRequest;
import com.cami_rehberi.api.entity.MekanTarihceBlok;

import java.util.List;

public interface IMekanTarihceBlokController {

    public List<MekanTarihceBlok> postMekanTarihceBlokEkle(MekanTarihceBlokEkleRequest mekanTarihceBlokEkleRequest);
}
