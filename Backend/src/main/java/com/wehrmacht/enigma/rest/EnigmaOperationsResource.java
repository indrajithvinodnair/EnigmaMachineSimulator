package com.wehrmacht.enigma.rest;

import com.wehrmacht.enigma.dataModels.EnigmaDataModel;
import com.wehrmacht.enigma.service.EnigmaEncoderDecoderService;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("wehrmacht/enigma")
public class EnigmaOperationsResource {

    @Resource
    private EnigmaEncoderDecoderService enigmaEncoderDecoderServiceImpl;

    @PostMapping("/encrypt")
    public EnigmaDataModel encrypt(@RequestBody EnigmaDataModel enigmaDataModel){
        return enigmaEncoderDecoderServiceImpl.getEncryptedMessage(enigmaDataModel);
    }

    /*render.com scalesdown the application if its inactive for 15 minutes,
     FE will invoke this api as soon as it is awake , thus waking the BE up from sleep.
     This would be an issue otherwise as the first request would take a lot of time to process.
     This is a workaround to keep the application alive. You can remove this and use
     the actuator health endpoint to keep the application alive. But it requires setting up the
     spring security and other configurations. This is a simple workaround for the same.
     */
    @GetMapping("/heartbeat")
    public boolean heartbeat(){
        return true;
    }
    
}
