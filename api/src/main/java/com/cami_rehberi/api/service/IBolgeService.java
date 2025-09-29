package com.cami_rehberi.api.service;

import com.cami_rehberi.api.entity.Bolge;

import java.util.List;

public interface IBolgeService {

    public Bolge postBolgeEkle(String ad);

    public List<Bolge> getTumBolgelerGetir();
}
