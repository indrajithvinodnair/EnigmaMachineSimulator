package com.wehrmacht.enigma.rest;

import com.wehrmacht.enigma.dataModels.EnigmaDataModel;
import com.wehrmacht.enigma.service.EnigmaEncoderDecoderService;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("wehrmacht/enigma")
public class EnigmaOperationsResource {

    @Resource
    private EnigmaEncoderDecoderService enigmaEncoderDecoderServiceImpl;

    @PostMapping("/encrypt")
    public EnigmaDataModel encrypt(@RequestBody EnigmaDataModel enigmaDataModel){
        return enigmaEncoderDecoderServiceImpl.getEncryptedMessage(enigmaDataModel);
    }
}
