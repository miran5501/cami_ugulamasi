package com.cami_rehberi.api.service.serviceImpl;

import com.cami_rehberi.api.dto.request.MekanEkleRequest;
import com.cami_rehberi.api.dto.request.MekanKartRequest;
import com.cami_rehberi.api.dto.response.MekanKartResponse;
import com.cami_rehberi.api.entity.*;
import com.cami_rehberi.api.repository.*;
import com.cami_rehberi.api.service.IMekanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.UUID;

@Service
public class MekanServiceImpl implements IMekanService {

    @Autowired
    private MekanRepository mekanRepository;

    @Autowired
    private MekanDetayRepository mekanDetayRepository;

    @Autowired
    private BolgeCeviriRepository bolgeCeviriRepository;

    @Autowired
    private MekanFotoRepository mekanFotoRepository;

    @Autowired
    private BolgeRepository bolgeRepository;

    @Override
    public List<MekanKartResponse> getAllMekanKartResponse(MekanKartRequest request) {
        // Dil kodu kontrolü (default tr)
        String dilKodu = (request.getDilKodu() == null || request.getDilKodu().isBlank())
                ? "tr"
                : request.getDilKodu();

        // Mekan listesi
        List<Mekan> mekanList = mekanRepository.findAll();

        // Çeviriler
        List<MekanDetay> mekanDetayList = mekanDetayRepository.findByDilKodu(dilKodu);
        List<BolgeCeviri> bolgeCeviriList = bolgeCeviriRepository.findByDilKodu(dilKodu);

        // Response listesi oluştur
        List<MekanKartResponse> responseList = mekanList.stream().map(m -> {
                    // Mekan çevirisi
                    MekanDetay ceviri = mekanDetayList.stream()
                            .filter(c -> c.getMekan().getId().equals(m.getId()))
                            .findFirst()
                            .orElse(null);

                    // Bölge çevirisi
                    BolgeCeviri bolgeCeviri = bolgeCeviriList.stream()
                            .filter(b -> b.getBolge().getId().equals(m.getBolge().getId()))
                            .findFirst()
                            .orElse(null);

                    // Kapak fotoğrafı
                    String kapakFoto = mekanFotoRepository
                            .findFirstByMekanIdAndIsKapakFotoTrue(m.getId())
                            .map(MekanFoto::getUrl)
                            .orElse(null);

                    // Mesafe hesapla (km)
                    Double mesafe = distanceInKm(
                            request.getEnlem(),
                            request.getBoylam(),
                            m.getEnlem(),
                            m.getBoylam()
                    );

                    return new MekanKartResponse(
                            m.getId(),
                            ceviri != null ? ceviri.getAd() : null,
                            ceviri != null ? ceviri.getAdres() : null,
                            kapakFoto,
                            bolgeCeviri != null ? bolgeCeviri.getAd() : null,
                            m.getEnlem(),
                            m.getBoylam(),
                            mesafe
                    );
                }).sorted(Comparator.comparing(MekanKartResponse::getMesafe))
                .toList();

        return responseList;
    }

    @Override
    public Mekan postMekanEkle(MekanEkleRequest mekanEkleRequest) {
        // Bolge entity'sini id ile bul
        UUID bolgeId = mekanEkleRequest.getBolgeId();
        Bolge bolge = bolgeRepository.findById(bolgeId)
                .orElseThrow(() -> new RuntimeException("Bölge bulunamadı"));

        // Mekan oluştur
        Mekan mekan = new Mekan();
        mekan.setTip(mekanEkleRequest.getTip());
        mekan.setBolge(bolge);
        mekan.setEnlem(mekanEkleRequest.getEnlem());
        mekan.setBoylam(mekanEkleRequest.getBoylam());
        mekan.setDefaultAd(mekanEkleRequest.getDefaultAd());

        // DB'ye kaydet
        return mekanRepository.save(mekan);
    }

    @Override
    public List<Mekan> getAllMekanGetir() {
        return mekanRepository.findAll();
    }


    private double distanceInKm(double lat1, double lon1, double lat2, double lon2) {
        final int R = 6371; // Dünya yarıçapı km
        double dLat = Math.toRadians(lat2 - lat1);
        double dLon = Math.toRadians(lon2 - lon1);
        double a = Math.sin(dLat/2) * Math.sin(dLat/2)
                + Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2))
                * Math.sin(dLon/2) * Math.sin(dLon/2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1-a));
        return R * c; // km cinsinden
    }

}
