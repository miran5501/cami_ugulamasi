package com.cami_rehberi.api.controller;

import com.cami_rehberi.api.dto.request.MekanFotoEkleRequest;
import com.cami_rehberi.api.entity.MekanFoto;

import java.util.List;

public interface IMekanFotoController {
    public List<MekanFoto> postMekanFotoEkle(MekanFotoEkleRequest mekanFotoEkleRequest);
}
