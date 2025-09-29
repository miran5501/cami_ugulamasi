package com.cami_rehberi.api.service;

import com.cami_rehberi.api.dto.request.MekanTarihceBlokEkleRequest;
import com.cami_rehberi.api.entity.MekanTarihceBlok;

import java.util.List;

public interface IMekanTarihceBlokService {

    public List<MekanTarihceBlok> postMekanTarihceBlokEkle(MekanTarihceBlokEkleRequest mekanTarihceBlokEkleRequest);
}
