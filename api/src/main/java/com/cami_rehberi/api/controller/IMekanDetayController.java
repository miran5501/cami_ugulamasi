package com.cami_rehberi.api.controller;

import com.cami_rehberi.api.dto.request.MekanDetayEkleRequest;
import com.cami_rehberi.api.entity.MekanDetay;

import java.util.List;

public interface IMekanDetayController {

    public List<MekanDetay> postMekanDetayEkleToplu(MekanDetayEkleRequest mekanDetayEkleRequest);
}
