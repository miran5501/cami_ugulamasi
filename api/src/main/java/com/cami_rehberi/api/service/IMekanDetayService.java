package com.cami_rehberi.api.service;

import com.cami_rehberi.api.dto.request.MekanDetayEkleRequest;
import com.cami_rehberi.api.entity.MekanDetay;

import java.util.List;

public interface IMekanDetayService {

    public List<MekanDetay> postMekanDetayEkleToplu(MekanDetayEkleRequest mekanDetayEkleRequest);
}
