package com.cami_rehberi.api.service.serviceImpl;

import com.cami_rehberi.api.entity.Bolge;
import com.cami_rehberi.api.repository.BolgeRepository;
import com.cami_rehberi.api.service.IBolgeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BolgeServiceImpl implements IBolgeService {

    @Autowired
    private BolgeRepository bolgeRepository;

    @Override
    public Bolge postBolgeEkle(String ad) {
        Bolge bolge = new Bolge();
        bolge.setDefaultAd(ad);
        return bolgeRepository.save(bolge);
    }

    @Override
    public List<Bolge> getTumBolgelerGetir() {
        // DB’deki tüm bölgeleri getirir
        return bolgeRepository.findAll();
    }
}
