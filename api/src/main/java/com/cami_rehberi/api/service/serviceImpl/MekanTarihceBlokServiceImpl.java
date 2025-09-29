package com.cami_rehberi.api.service.serviceImpl;

import com.cami_rehberi.api.dto.request.MekanTarihceBlokEkleRequest;
import com.cami_rehberi.api.entity.MekanTarihceBlok;
import com.cami_rehberi.api.repository.MekanRepository;
import com.cami_rehberi.api.repository.MekanTarihceBlokRepository;
import com.cami_rehberi.api.service.IMekanTarihceBlokService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MekanTarihceBlokServiceImpl implements IMekanTarihceBlokService {

    @Autowired
    private MekanRepository mekanRepository;

    @Autowired
    private MekanTarihceBlokRepository blokRepository;

    @Override
    public List<MekanTarihceBlok> postMekanTarihceBlokEkle(MekanTarihceBlokEkleRequest request) {
        System.out.println("➡️ API çağrıldı: postMekanTarihceBlokEkle");
        System.out.println("📌 Gelen Mekan ID: " + request.getMekanId());

        if (request.getBloklar() == null || request.getBloklar().isEmpty()) {
            System.out.println("⚠️ Blok listesi boş geldi!");
            throw new RuntimeException("Blok listesi boş!");
        }

        // Her blok için detayları yazdıralım
        request.getBloklar().forEach(dto -> {
            System.out.println("---- Yeni Blok ----");
            System.out.println("Dil: " + dto.getDilKodu());
            System.out.println("Paragraf: " + dto.getParagraf());
            System.out.println("ResimUrl: " + dto.getResimUrl());
            System.out.println("isBaslik: " + dto.getIsBaslik());
            System.out.println("Sıra: " + dto.getSira());
        });

        var mekan = mekanRepository.findById(request.getMekanId())
                .orElseThrow(() -> new RuntimeException("Mekan bulunamadı!"));

        List<MekanTarihceBlok> blokList = request.getBloklar().stream().map(dto -> {
            MekanTarihceBlok blok = new MekanTarihceBlok();
            blok.setMekan(mekan);
            blok.setDilKodu(dto.getDilKodu());
            blok.setParagraf(dto.getParagraf());
            blok.setResimUrl(dto.getResimUrl());
            blok.setIsBaslik(dto.getIsBaslik());
            blok.setSira(dto.getSira());
            return blok;
        }).toList();

        System.out.println("💾 Kaydedilecek blok sayısı: " + blokList.size());

        return blokRepository.saveAll(blokList);
    }

}

