package com.cami_rehberi.api.controller;

import com.cami_rehberi.api.dto.request.BolgeCeviriEkleRequest;
import com.cami_rehberi.api.entity.BolgeCeviri;

import java.util.List;

public interface IBolgeCeviriController {

    public List<BolgeCeviri> postBolgeCeviriEkle(BolgeCeviriEkleRequest bolgeCeviriEkleRequest);
}
