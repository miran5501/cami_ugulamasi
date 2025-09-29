package com.cami_rehberi.api.service;

import com.cami_rehberi.api.dto.request.BolgeCeviriEkleRequest;
import com.cami_rehberi.api.entity.BolgeCeviri;

import java.util.List;

public interface IBolgeCeviriService {

    public List<BolgeCeviri> postBolgeCeviriEkle(BolgeCeviriEkleRequest bolgeCeviriEkleRequest);
}
