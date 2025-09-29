package com.cami_rehberi.api.service;

import com.cami_rehberi.api.dto.request.MekanFotoEkleRequest;
import com.cami_rehberi.api.entity.MekanFoto;

import java.util.List;

public interface IMekanFotoService {
    public List<MekanFoto> postMekanFotoEkle(MekanFotoEkleRequest mekanFotoEkleRequest);
}
