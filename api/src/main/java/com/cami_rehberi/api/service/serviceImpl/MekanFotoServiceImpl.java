package com.cami_rehberi.api.service.serviceImpl;

import com.cami_rehberi.api.dto.request.MekanFotoEkleRequest;
import com.cami_rehberi.api.entity.MekanFoto;
import com.cami_rehberi.api.repository.MekanFotoRepository;
import com.cami_rehberi.api.repository.MekanRepository;
import com.cami_rehberi.api.service.IMekanFotoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MekanFotoServiceImpl implements IMekanFotoService {

    @Autowired
    private MekanRepository mekanRepository;

    @Autowired
    private MekanFotoRepository mekanFotoRepository;

    @Override
    public List<MekanFoto> postMekanFotoEkle(MekanFotoEkleRequest request) {
        var mekan = mekanRepository.findById(request.getMekanId())
                .orElseThrow(() -> new RuntimeException("Mekan bulunamadı"));

        List<MekanFoto> fotoList = request.getFotolar().stream().map(dto -> {
            MekanFoto foto = new MekanFoto();
            foto.setMekan(mekan);
            foto.setUrl(dto.getUrl());
            foto.setIsKapakFoto(dto.getIsKapakFoto());
            foto.setSira(dto.getSira());
            return foto;
        }).toList();

        return mekanFotoRepository.saveAll(fotoList);
    }
}

