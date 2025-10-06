package com.cami_rehberi.api.service.serviceImpl;

import com.cami_rehberi.api.common.exception.BadRequestException;
import com.cami_rehberi.api.common.exception.NotFoundException;
import com.cami_rehberi.api.dto.MekanFotoDto;
import com.cami_rehberi.api.dto.request.MekanEkleRequest;
import com.cami_rehberi.api.dto.request.MekanKartRequest;
import com.cami_rehberi.api.dto.response.MekanDetayResponse;
import com.cami_rehberi.api.dto.response.MekanKartResponse;
import com.cami_rehberi.api.dto.response.MekanTarihceResponse;
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

    @Autowired private MekanRepository mekanRepository;
    @Autowired private MekanDetayRepository mekanDetayRepository;
    @Autowired private BolgeCeviriRepository bolgeCeviriRepository;
    @Autowired private MekanFotoRepository mekanFotoRepository;
    @Autowired private BolgeRepository bolgeRepository;
    @Autowired private MekanTarihceBlokRepository mekanTarihceBlokRepository;

    // ✅ Ortak dil kodu kontrolü
    private String resolveDilKodu(String dilKodu) {
        String aktifDil = (dilKodu == null || dilKodu.isBlank()) ? "tr" : dilKodu.toLowerCase();

        // Sadece tr, en, el geçerli
        if (!aktifDil.equals("tr") && !aktifDil.equals("en") && !aktifDil.equals("el")) {
            throw new BadRequestException("Geçersiz dil kodu: " + aktifDil + ". Sadece tr, en, el destekleniyor.");
        }

        return aktifDil;
    }


    // ✅ Ortak mesafe hesaplama
    private Double calculateDistance(Double lat1, Double lon1, Double lat2, Double lon2) {
        if (lat1 == null || lon1 == null || lat2 == null || lon2 == null) return null;

        final int R = 6371; // Dünya yarıçapı km
        double dLat = Math.toRadians(lat2 - lat1);
        double dLon = Math.toRadians(lon2 - lon1);
        double a = Math.sin(dLat/2) * Math.sin(dLat/2)
                + Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2))
                * Math.sin(dLon/2) * Math.sin(dLon/2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1-a));
        return R * c;
    }

    @Override
    public List<MekanKartResponse> getAllMekanKartResponse(MekanKartRequest request) {
        String dilKodu = resolveDilKodu(request.getDilKodu());

        List<Mekan> mekanList = mekanRepository.findAll();
        List<MekanDetay> mekanDetayList = mekanDetayRepository.findByDilKodu(dilKodu);
        List<BolgeCeviri> bolgeCeviriList = bolgeCeviriRepository.findByDilKodu(dilKodu);

        return mekanList.stream()
                .map(m -> {
                    MekanDetay ceviri = mekanDetayList.stream()
                            .filter(c -> c.getMekan().getId().equals(m.getId()))
                            .findFirst()
                            .orElse(null);

                    BolgeCeviri bolgeCeviri = bolgeCeviriList.stream()
                            .filter(b -> b.getBolge().getId().equals(m.getBolge().getId()))
                            .findFirst()
                            .orElse(null);

                    String kapakFoto = mekanFotoRepository
                            .findFirstByMekanIdAndIsKapakFotoTrue(m.getId())
                            .map(MekanFoto::getUrl)
                            .orElse(null);

                    Double mesafe = calculateDistance(
                            request.getEnlem(),
                            request.getBoylam(),
                            m.getEnlem(),
                            m.getBoylam()
                    );

                    return new MekanKartResponse(
                            m.getId(),
                            ceviri != null ? ceviri.getAd() : m.getDefaultAd(),
                            ceviri != null ? ceviri.getAdres() : null,
                            kapakFoto,
                            bolgeCeviri != null ? bolgeCeviri.getAd() : m.getBolge().getDefaultAd(),
                            m.getEnlem(),
                            m.getBoylam(),
                            mesafe
                    );
                })
                .sorted(Comparator.comparing(
                        MekanKartResponse::getMesafe,
                        Comparator.nullsLast(Double::compareTo)
                ))
                .toList();
    }

    @Override
    public Mekan postMekanEkle(MekanEkleRequest mekanEkleRequest) {
        Bolge bolge = bolgeRepository.findById(mekanEkleRequest.getBolgeId())
                .orElseThrow(() -> new NotFoundException("Bölge bulunamadı"));

        Mekan mekan = new Mekan();
        mekan.setTip(mekanEkleRequest.getTip());
        mekan.setBolge(bolge);
        mekan.setEnlem(mekanEkleRequest.getEnlem());
        mekan.setBoylam(mekanEkleRequest.getBoylam());
        mekan.setDefaultAd(mekanEkleRequest.getDefaultAd());

        return mekanRepository.save(mekan);
    }

    @Override
    public List<Mekan> getAllMekanGetir() {
        return mekanRepository.findAll();
    }

    @Override
    public MekanDetayResponse getMekanDetayGetir(MekanKartRequest request, UUID id) {
        String dilKodu = resolveDilKodu(request.getDilKodu());

        Mekan mekan = mekanRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Mekan bulunamadı"));

        MekanDetay mekanDetay = mekanDetayRepository.findByMekanAndDilKodu(mekan, dilKodu)
                .orElseThrow(() -> new NotFoundException("Mekan detay bulunamadı"));

        BolgeCeviri bolgeCeviri = bolgeCeviriRepository.findByBolgeAndDilKodu(mekan.getBolge(), dilKodu)
                .orElse(null);

        List<MekanFotoDto> fotoList = mekanFotoRepository.findByMekanOrderBySira(mekan)
                .stream()
                .map(f -> new MekanFotoDto(f.getUrl(), f.getIsKapakFoto(), f.getSira()))
                .toList();

        Double mesafe = calculateDistance(
                request.getEnlem(),
                request.getBoylam(),
                mekan.getEnlem(),
                mekan.getBoylam()
        );

        return new MekanDetayResponse(
                mekan.getId(),
                mekanDetay.getAd(),
                mekanDetay.getAdres(),
                bolgeCeviri != null ? bolgeCeviri.getAd() : mekan.getBolge().getDefaultAd(),
                mekan.getEnlem(),
                mekan.getBoylam(),
                mesafe,
                fotoList
        );
    }

    @Override
    public List<MekanTarihceResponse> getMekanDetayTarihce(UUID id, String dilKodu) {
        String aktifDilKodu = resolveDilKodu(dilKodu);

        Mekan mekan = mekanRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Mekan bulunamadı"));

        return mekanTarihceBlokRepository.findByMekanAndDilKoduOrderBySira(mekan, aktifDilKodu)
                .stream()
                .map(b -> new MekanTarihceResponse(
                        b.getParagraf(),
                        b.getResimUrl(),
                        b.getIsBaslik(),
                        b.getSira()
                ))
                .toList();
    }
}
