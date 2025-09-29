package com.cami_rehberi.api.service.serviceImpl;

import com.cami_rehberi.api.dto.request.MekanDetayEkleRequest;
import com.cami_rehberi.api.entity.Mekan;
import com.cami_rehberi.api.entity.MekanDetay;
import com.cami_rehberi.api.repository.MekanDetayRepository;
import com.cami_rehberi.api.repository.MekanRepository;
import com.cami_rehberi.api.service.IMekanDetayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MekanDetayServiceImpl implements IMekanDetayService {

    @Autowired
    private MekanRepository mekanRepository;

    @Autowired
    private MekanDetayRepository mekanDetayRepository;

    @Override
    public List<MekanDetay> postMekanDetayEkleToplu(MekanDetayEkleRequest request) {
        // Mekan'ı id ile bul
        Mekan mekan = mekanRepository.findById(request.getMekanId())
                .orElseThrow(() -> new RuntimeException("Mekan bulunamadı"));

        // Listeyi dön
        List<MekanDetay> kaydedilenDetaylar = request.getMekanDetayDtoList().stream().map(dto -> {
            MekanDetay detay = new MekanDetay();
            detay.setMekan(mekan);
            detay.setDilKodu(dto.getDilKodu());
            detay.setAd(dto.getAd());
            detay.setKisaAciklama(dto.getKisaAciklama());
            detay.setAdres(dto.getAdres());
            return mekanDetayRepository.save(detay);
        }).toList();

        return kaydedilenDetaylar;
    }
}


