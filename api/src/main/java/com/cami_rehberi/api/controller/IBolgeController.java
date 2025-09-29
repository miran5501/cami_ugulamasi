package com.cami_rehberi.api.controller;

import com.cami_rehberi.api.entity.Bolge;

import java.util.List;

public interface IBolgeController {

    public Bolge postBolgeEkle(String ad);

    public List<Bolge> getTumBolgelerGetir();
}
