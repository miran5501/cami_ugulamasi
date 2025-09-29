package com.cami_rehberi.api.controller.controllerImpl;

import com.cami_rehberi.api.controller.IBolgeCeviriController;
import com.cami_rehberi.api.dto.request.BolgeCeviriEkleRequest;
import com.cami_rehberi.api.entity.BolgeCeviri;
import com.cami_rehberi.api.service.IBolgeCeviriService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/bolge-ceviri")
public class BolgeCeviriControllerImpl implements IBolgeCeviriController {

    @Autowired
    private IBolgeCeviriService bolgeCeviriService;

    @Override
    @PostMapping("/ekle")
    public List<BolgeCeviri> postBolgeCeviriEkle(@RequestBody BolgeCeviriEkleRequest request) {
        return bolgeCeviriService.postBolgeCeviriEkle(request);
    }
}

