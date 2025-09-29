package com.cami_rehberi.api.service.serviceImpl;

import com.cami_rehberi.api.dto.request.BolgeCeviriEkleRequest;
import com.cami_rehberi.api.entity.BolgeCeviri;
import com.cami_rehberi.api.repository.BolgeCeviriRepository;
import com.cami_rehberi.api.repository.BolgeRepository;
import com.cami_rehberi.api.service.IBolgeCeviriService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BolgeCeviriServiceImpl implements IBolgeCeviriService {

    @Autowired
    private BolgeRepository bolgeRepository;

    @Autowired
    private BolgeCeviriRepository bolgeCeviriRepository;

    @Override
    public List<BolgeCeviri> postBolgeCeviriEkle(BolgeCeviriEkleRequest request) {
        var bolge = bolgeRepository.findById(request.getBolgeId())
                .orElseThrow(() -> new RuntimeException("Bölge bulunamadı"));

        List<BolgeCeviri> ceviriList = request.getCeviriler().stream().map(dto -> {
            BolgeCeviri ceviri = new BolgeCeviri();
            ceviri.setBolge(bolge);
            ceviri.setDilKodu(dto.getDilKodu());
            ceviri.setAd(dto.getAd());
            return ceviri;
        }).toList();

        return bolgeCeviriRepository.saveAll(ceviriList);
    }
}


